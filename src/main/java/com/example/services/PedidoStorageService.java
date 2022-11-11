package com.example.services;

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
import com.example.exception.PedidoIncorrectoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.dto.UsuarioDTO;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.model.User;
import com.example.model.Estado.Estado;
import com.example.model.Estado.PendienteAtencion;
import com.example.model.Estado.Reservado;
import com.example.repository.EstadoDBRepository;
import com.example.repository.PedidoDBRepository;
import com.example.repository.PosicionDBRepository;
import com.example.repository.UserRepository;

@Service
public class PedidoStorageService {

	@Autowired
	private PedidoDBRepository pedidoDBRepository;
	@Autowired
	private PosicionDBRepository posDBRepository;
	@Autowired
	private UserRepository userRepository;

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
						System.out.println("Index: " + index);
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

	//	public void reservar(Long id, String usuarioDTO) throws IOException {
//		Pedido pedido = pedidoDBRepository.findById(id).get();
//		pedido.setState("reservado");
//		pedido.setEncargado(usuarioDTO);
	@Transactional
	public void reservar(Long id, String username) throws IOException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		Optional<User> encargado = userRepository.findByUsername(username);
		System.out.println("Estoy en el metodo reservar");
		Estado reservado = new Estado(2, "reservado", "Reservado", "#87d068");
		pedido.setState(reservado);
		//User encargado = pedido.getPropietario(); // Editor encargado de darle resolución al Pedido
		if(encargado.isPresent()) {
			System.out.println("TIENE ENCARGADO");
			User encargadoToSave = encargado.get();
			userRepository.save(encargadoToSave);
			pedido.setEncargado(encargadoToSave);
		};
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
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		map.put("reservado", 0);
		map.put("Pendiente atencion", 0);

		map.put("propios", 0);
		map.put("reservado", pedidoDBRepository.countByEncargadoUsernameAndStateValue(username, "reservado"));
		map.put("Pendiente atencion", pedidoDBRepository.countByStateValue("PendAtencion"));
		map.put("propios", pedidoDBRepository.countByPropietarioUsername(username));

//		map.put("Pendiente atencion", pedidoDBRepository.countByPropietarioUsernameAndStateValue(username, "PendAtencion"));
//		map.put("reservado", pedidoDBRepository.countByPropietarioUsernameAndStateValue(username, "reservado"));

		return map;
	}

}
