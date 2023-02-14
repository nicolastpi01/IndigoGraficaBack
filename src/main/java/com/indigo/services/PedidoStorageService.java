package com.indigo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.indigo.dto.UsuarioDTO;
import com.indigo.exception.CustomException;
import com.indigo.exception.PedidoIncorrectoException;
import com.indigo.model.FileDB;
import com.indigo.model.Pedido;
import com.indigo.model.Requerimiento;
import com.indigo.model.User;
import com.indigo.model.Estado.Estado;
import com.indigo.model.Estado.PendienteAtencion;
import com.indigo.model.Estado.Reservado;
import com.indigo.repository.EstadoDBRepository;
import com.indigo.repository.PedidoDBRepository;
import com.indigo.repository.PosicionDBRepository;
import com.indigo.repository.SolucionDBRepository;
import com.indigo.repository.UserRepository;

@Service
public class PedidoStorageService {

	@Autowired
	private PedidoDBRepository pedidoDBRepository;
	@Autowired
	private PosicionDBRepository posDBRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SolucionDBRepository solutionDBRepository;

	@Transactional
	public Pedido store(MultipartFile[] files, Pedido pedido, List<List<Requerimiento>> requerimientos) throws PedidoIncorrectoException {

		List<FileDB> filesDB = new ArrayList<>();
		pedido.validar();
		for(int index = 0;index < files.length; index++) {
			try {
				MultipartFile file = Arrays.asList(files).get(index);
				FileDB FileDB = new FileDB(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes());
				if(!requerimientos.isEmpty()) {
					if(requerimientos.get(index) != null) {
						//FileDB.setRequerimientos(requerimientos.get(index).stream().collect(Collectors.toSet()));
						//FileDB.setRequerimientos(requerimientos.get(index).stream().collect(Collectors.toList()));
					}
				}
				filesDB.add(FileDB);
			} catch (IOException e) {
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
		}

		pedido.setFiles(filesDB);
		return pedidoDBRepository.save(pedido);
	}

	@Transactional(readOnly=true)
	public ArrayList<Pedido> getAllByState(String value) {
		return pedidoDBRepository.findByStateValue(value) ;
	}

	@Transactional(readOnly=true)
	public List<Pedido> getAllByPropietario(String username) {
		return pedidoDBRepository.findByPropietarioUsername(username);
	}
	
	/*
	@Transactional(readOnly=true)
	public List<Pedido> getAllCarrito(String username, String stateValue) {
		return pedidoDBRepository.findByPropietarioUsernameAndStateValueDistinct(username, stateValue);
	}
	*/
	
	@Transactional(readOnly=true)
	public ArrayList<Pedido> getAllPedidos(String username) {
		// Si soy Editor quiero todos los pedidos Pend. Atención, si soy Cliente quiero todos los Pedidos donde soy el Propietario
		Optional<User> optUsuario = userRepository.findByUsername(username);
		if(optUsuario.isPresent()) {
			User usuario = optUsuario.get();
			if(usuario.esEditor()) {
				return pedidoDBRepository.findByStateValue("pendAtencion");
			}
			else {
				return pedidoDBRepository.findByPropietarioUsername(username);
			}
		}
		else {
			return new ArrayList<Pedido>();
		}
	};

	@Transactional
	public void reservar(Long id, String username) throws CustomException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		Optional<User> encargado = userRepository.findByUsername(username);
		if(pedido.getIsEditing()) {
			// Lo están Editando lanzo la Excepción
			throw new CustomException(HttpStatus.FORBIDDEN, "No puede reservar el Pedido en este momento, ya que el Cliente lo esta Editando");
		}
		else {
			Estado reservado = new Estado(2, "reservado", "Reservado", "#87d068");
			pedido.setState(reservado);
			//User encargado = pedido.getPropietario(); // Editor encargado de darle resolución al Pedido
			if(encargado.isPresent()) {
				User encargadoToSave = encargado.get();
				userRepository.save(encargadoToSave);
				pedido.setEncargado(encargadoToSave);
			}
		}
		pedidoDBRepository.save(pedido);
	};

	@Transactional
	public Pedido actualizar(Pedido pedido) throws IllegalArgumentException {
		return pedidoDBRepository.save(pedido);
	}

	@Transactional
	public Pedido create(Pedido pedido)  throws IllegalArgumentException {
		return pedidoDBRepository.save(pedido);
	}

	@Transactional
	public void delete(Long id) throws IOException {
		pedidoDBRepository.deleteById(id);
	}

	@Transactional(readOnly=true)
	public Optional<Pedido> findPedido(Long id) {
		return pedidoDBRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public Map<String, Integer> getResumeByOwner(String username) {
		Map<String, Integer> map = new HashMap<>();
		map.put("reservado", 0);
		map.put("Pendiente atencion", 0);
		map.put("Pendiente revision", 0);
		map.put("Rechazado", 0);
		map.put("Finalizados", 0);
		map.put("PROPIOS", 0);
		Optional<User> optUsuario = userRepository.findByUsername(username);
		if(optUsuario.isPresent()) {
			User usuario = optUsuario.get();
			if(usuario.esEditor()) {
				map.put("Pendiente atencion", pedidoDBRepository.countByStateValue("pendAtencion"));
				map.put("reservado", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "reservado"));
				map.put("Pendiente revision", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "pendRevision"));
				map.put("Rechazado", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "rechazado"));
				map.put("Finalizados", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "finalizados"));
			}
			else {
				map.put("Pendiente atencion", pedidoDBRepository.countByPropietarioUsernameAndStateValue(username, "pendAtencion"));
				map.put("reservado", pedidoDBRepository.countByPropietarioUsernameAndStateValue(username, "reservado"));
				map.put("Pendiente revision", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "pendRevision"));
				map.put("Rechazado", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "rechazado"));
				map.put("Finalizados", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "finalizados"));
			}
			map.put("PROPIOS", pedidoDBRepository.countByPropietarioUsername(username));
		}
		return map;
	};
	
	@Transactional
	public void resolverRechazados(Long id) throws CustomException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		if(pedido.haveFiles()) {
			if(pedido.allFilesHaveSolution()) {
				if(pedido.allFilesHaveBeenReplacement()) {
					// Todo bien, cambio el Estado del Pedido y continuo
					Estado pend = new Estado(3, "pendRevision", "Pendiente de revisión", "rgba(167, 37, 165, 0.755)"); 
					pedido.setState(pend);
					pedidoDBRepository.save(pedido);
				}
				else {
					throw new CustomException(HttpStatus.BAD_REQUEST, "Algún File cuenta con una Solución rechazada por el Cliente");
				}	
			}
			else {
				throw new CustomException(HttpStatus.BAD_REQUEST, "Algún File no tiene Solucion");
			}
		}
		else {
			// No tiene Files
			if(pedido.haveSolution()) {
				if(pedido.getSolutions().get(0).isHasReplacement()) {
					// Si solo tengo una solución debo verificar que la misma tenga reemplazo!
					// Todo bien, cambio el Estado del Pedido y continuo
					Estado pend = new Estado(3, "pendRevision", "Pendiente de revisión", "rgba(167, 37, 165, 0.755)"); // Cambiar resuelto por una cte, Color Violeta
					pedido.setState(pend);
					pedidoDBRepository.save(pedido);
				}
				else {
					throw new CustomException(HttpStatus.BAD_REQUEST, "El file cuenta con una Solución rechazada por el Cliente");
				}
			}
			else {
				throw new CustomException(HttpStatus.BAD_REQUEST, "No se puede resolver un Pedido que no cuenta con Solución");
			}
		}
	};
	
	@Transactional
	public void resolver(Long id) throws CustomException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		if(pedido.hasPayment()) {
			if(pedido.haveFiles()) {
				if(pedido.allFilesHaveSolution()) {
					// Todo bien, cambio el Estado del Pedido y continuo
					Estado reservado = new Estado(3, "pendRevision", "Pendiente de revisión", "rgba(167, 37, 165, 0.755)"); // Cambiar resuelto por una cte, Color Violeta
					pedido.setState(reservado);
					pedidoDBRepository.save(pedido);
				}
				else {
					throw new CustomException(HttpStatus.BAD_REQUEST, "Algún File no tiene Solucion");
				}
			}
			else {
				// No tiene Files
				if(pedido.haveSolution()) {
					// Todo bien, cambio el Estado del Pedido y continuo
					Estado reservado = new Estado(3, "pendRevision", "Pendiente de revisión", "rgba(167, 37, 165, 0.755)"); // Cambiar resuelto por una cte, Color Violeta
					pedido.setState(reservado);
					pedidoDBRepository.save(pedido);
				}
				else {
					throw new CustomException(HttpStatus.BAD_REQUEST, "No se puede resolver un Pedido que no cuenta con Solución");
				}
			}
		}
		else {
			throw new CustomException(HttpStatus.FORBIDDEN, "No se puede resolver un Pedido que no cuenta con un pago por parte del Cliente Propietario");
		}
			
	};

	@Transactional
	public void notifyPayment(Long id, String userName) throws CustomException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		Optional<User> optUser = userRepository.findByUsername(userName);
		if(optUser.isPresent()) {
			User user = optUser.get();
			if(!user.esEditor()) {
				throw new CustomException(HttpStatus.FORBIDDEN,"Solo un Usuario con rango de Editor puede realizar la acción requerida!");
			}
			if(user.esEditor() && !(user.getId().equals(pedido.getEncargado().getId()))) {
				throw new CustomException(HttpStatus.FORBIDDEN,"El editor no coincide con el encargado de darle resolución al Pedido, solo este último puede resolverlo!");
			}
			// Falta la excepción que se dispara si el Pedido no tiene un Presupuesto asociado
			// Lo comento porque la generación del presupuesto no esta funcionando actualmente
			
			if(!pedido.hasBudget()) {
				throw new CustomException(HttpStatus.FORBIDDEN, "No se puede notificar el pago de un Pedido para el cual no se envío un Presupuesto, envíe el Presupuesto primero!");
			}
			
			if(!pedido.getSendBudgetMail()) {
				throw new CustomException(HttpStatus.FORBIDDEN, "No se puede notificar el pago de un Pedido para el cual no se envio por mail el Presupuesto al Cliente, envie el mail al Cliente primero!");
			}
			pedido.setHasPayment(true);
			this.pedidoDBRepository.save(pedido);
		}
		else {
			throw new CustomException(HttpStatus.NOT_FOUND,"No exíte un Cliente con ese nombre de Usuario");
		}
	}

	@Transactional
	public void sendApproveSolution(Pedido pedido, String userName) throws CustomException {
		Optional<User> optUser = userRepository.findByUsername(userName);
		if(optUser.isPresent()) {
			User user = optUser.get();
			/* Cliente o Editor pueden realizar la acción, pero lo que habria que validar es si el token es valido!!
			if(user.esEditor() || user.esCliente()) {
				throw new CustomException(HttpStatus.FORBIDDEN,"Solo un Cliente puede realizar la acción requerida!");
			}
			*/
			if(!(user.getId().equals(pedido.getPropietario().getId()))) {
				throw new CustomException(HttpStatus.FORBIDDEN,"El Cliente que intenta realizar la acción no coincide con el Propietario del Pedido!");
			}
			this.pedidoDBRepository.save(pedido);
		}
		else {
			throw new CustomException(HttpStatus.NOT_FOUND,"No exíte un Cliente con ese nombre de Usuario");
		}
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public String revisar(Long id) throws CustomException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		String message = "";
		if(pedido.allSolutionsWasApproved()) {
			// Todo bien, todas las soluciones estan aprobadas entonces el Pedido pasa a 'Finalizado'
			Estado finalizado = new Estado(5, "finalizados", "Finalizado", "darkorange");
			pedido.setState(finalizado);
			message = "Todas las soluciones fueron aprobadas!";
		}
		else {
			// Pedido tiene Soluciones Desaprobadas o tiene Soluciones sin indicar conformidad
			if(pedido.existsSolutionsWithoutAgreement()) {
				// Exíten soluciones sin conformidad (no aprobo ni desaprobo las mismas), lanza excepción! 
				throw new CustomException(HttpStatus.BAD_REQUEST, "Exísten soluciones para las cuáles no se ha brindado una conformidad-- Aprobado o Desaprobado. Indique conformidad y pruebe de nuevo");
			}
			else {
				// Exísten soluciones desaprobadas, entonces pongo las Aprobadas como no visibles (de este modo el Cliente ya no puede interactuar sobre las
				// mismas desde el Carrito), paso el Pedido a 'Rechazado'
				// De todas formas una vez el Pedido esta marcado como 'Rechazado' el Cliente no deberia poder interactuar con las Soluciones del pedido,
				// En este caso con el Pedido con Estado 'Rechazado'. Debe esperar que el Editor brinde una nueva Solución
				pedido.getSolutions().forEach(sol -> {
					if(sol.getApproved().booleanValue() == true) {
						sol.setVisible(false);
						this.solutionDBRepository.save(sol);
					}
				});
				Estado finalizado = new Estado(4, "rechazado", "Rechazado", "#f6180d");
				pedido.setState(finalizado);
				message = "Se marcaron soluciones como desaprobadas, y el Editor las revisará a la brevedad";
			}
		}
		this.pedidoDBRepository.save(pedido);
		return message;
	};
	
	@Transactional
	public void allowsEdit(Long id) throws CustomException {
		Optional<Pedido> pedidoOpt = pedidoDBRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			if(pedido.getState().getValue().equals("reservado")) {
				throw new CustomException(HttpStatus.FORBIDDEN, "El pedido se encuentra 'Reservado'. De ser necesaria la edición de este pedido pongase en contacto con el Editor por medio del 'Chat' para concordar como continuar");
			}
			else {
				pedido.setIsEditing(true);
				this.pedidoDBRepository.save(pedido);
			}
		}
		else {
			throw new CustomException(HttpStatus.NOT_FOUND,"No exíte un Cliente con ese nombre de Usuario");
		}
	}

	

}
