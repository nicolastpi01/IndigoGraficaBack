package com.example.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.controllers.dto.UsuarioDTO;
import com.example.controllers.exception.CustomException;
import com.example.controllers.exception.PedidoIncorrectoException;
import com.example.controllers.message.ResponseMessage;
import com.example.controllers.model.Pedido;
import com.example.controllers.model.Requerimiento;
import com.example.controllers.model.Estado.Estado;
import com.example.controllers.model.Estado.PendienteAtencion;
import com.example.controllers.objects.ApproveSolution;
import com.example.controllers.repository.EstadoDBRepository;
import com.example.controllers.security.authService.UserDetailsImpl;
import com.example.controllers.security.jwt.JwtUtils;
import com.example.controllers.services.EstadoService;
import com.example.controllers.services.PedidoStorageService;

@Controller
public class PedidoController {
	@Autowired
	private EstadoService estadoService;

	@Autowired
	private PedidoStorageService pedidoService;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/pedidos/create")
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido, @RequestHeader("authorization") String authorization) {
		//String message = "";
	    try {
			//busco al usuario de este token
			String token = authorization.split(" ")[1];
			String userName = jwtUtils.getUserNameFromJwtToken(token);
			Estado pendAtencion = new Estado(1, "pendAtencion", "Pendiente de atención", "#2db7f5"); //new PendienteAtencion(); // // Agrego el Estado, en este caso PendAtención
			//estadoService.save(pendAtencion);
			pedido.setState(pendAtencion);
			
			
			if(pedido.getAlto() == null) {
				pedido.setAlto(pedido.getTipo().getAlto());
			}
			if(pedido.getAncho() == null) {
				pedido.setAncho(pedido.getTipo().getAncho());
			}
			if(pedido.getTipografia() == null) {
				pedido.setTipografia(pedido.getTipo().getTipografia());
			}
			//pedido.setPropietario(userName);
			Pedido pedidoRet = pedidoService.create(pedido);
	      //message = "Se Agrego el pedido: " + pedido.getNombre();
			return ResponseEntity.status(HttpStatus.OK).body(pedidoRet);
		} catch (Exception e) {
	      //message = "No se pudo agregar el pedido: " + pedido.getNombre() + "!";
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pedido);
	    }
	}
	
	  @GetMapping("/pedidos")
	  @ResponseBody
	  public ResponseEntity<ArrayList<Pedido>> getPedidosByState(@RequestParam String state) {
	    ArrayList<Pedido> pedidos = pedidoService.getAllByState(state);
	    //List<PedidoDTO> pedidosDTO = pedidos.stream().map(pedido -> (new PedidoDTO(pedido))).collect(Collectors.toList());
	    return ResponseEntity.ok().body(pedidos);
	  }
	  
	@GetMapping("/todos")
	@ResponseBody
	public ResponseEntity<List<Pedido>> getAllPedidos(@RequestHeader("authorization") String authorization) {
		String token = authorization.split(" ")[1];
		String userName = jwtUtils.getUserNameFromJwtToken(token);
	    ArrayList<Pedido> pedidos = pedidoService.getAllPedidos(userName);
	    //List<PedidoDTO> pedidosDTO = pedidos.stream().map(pedido -> (new PedidoDTO(pedido))).collect(Collectors.toList());
	    return ResponseEntity.ok().body(pedidos);
	}

	@GetMapping("/pedidos/porUsuario")
	@ResponseBody
	public ResponseEntity<List<Pedido>> getPedidosByPropietario(@RequestHeader("authorization") String authorization) {
		String token = authorization.split(" ")[1];
		String userName = jwtUtils.getUserNameFromJwtToken(token);
		List<Pedido> pedidos = pedidoService.getAllByPropietario(userName);
		return ResponseEntity.ok().body(pedidos);
	}
	
	
	@GetMapping("/pedidos/resumen")
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> getResumen(@RequestHeader("authorization") String authorization) {
		String token = authorization.split(" ")[1];
		String userName = jwtUtils.getUserNameFromJwtToken(token);
		Map<String, Integer> pedidos = pedidoService.getResumeByOwner(userName);
		return ResponseEntity.ok().body(pedidos);
	}
	  
	  @PutMapping("/pedidos/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> reservar(@PathVariable Long id, @RequestHeader("authorization") String authorization) {
		String message = "";
		try {
			String token = authorization.split(" ")[1];
			String userName = jwtUtils.getUserNameFromJwtToken(token);
			pedidoService.reservar(id, userName);
			message = "Se reservo el pedido con id: " + id;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (CustomException e) { // Revisar el try catch. Atomizar las excepciones.. Ver 3.3 en docu discord
		    //message = "No se pudo reservar el pedido con id: " + id + "!";
		    return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  }
	  
	  
	  @PutMapping("/pedidos/AllowsEdit/{id}")
	  @ResponseBody
	  // Revisa que el Pedido no esté en un Estado distinto de 'pendAtención' si no es asi lanza Excep
	  // Si esta todo bien no lanza excepción y pone el Pedido en 'isEditing' = true
	  // Desde el Front, si esta mal lanza Excep y no permite avanzar, si esta bien Ok no retorna nada, y en la otra pantalla
	  // de Editar ya trae el pedido desde db con 'isEditing' = true
	  public ResponseEntity<ResponseMessage> AllowsEdit(@PathVariable Long id) {
		String message = "";
		try {
			pedidoService.allowsEdit(id);
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (CustomException e) {
		    return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  };
	  
	  @PutMapping("/pedidos/update")
	  @ResponseBody
	  public ResponseEntity<Pedido> update(@RequestBody Pedido pedido) {
		  Pedido pedidoActualizado = pedidoService.actualizar(pedido);
		  return ResponseEntity.status(HttpStatus.OK).body(pedidoActualizado);
	  }
	  
	  
	  @DeleteMapping("/pedidos/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> eliminar(@PathVariable Long id) {
		  String message = "";
			try {
				pedidoService.delete(id);
				message = "Se elimino el pedido con id: " + id;
			    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}
			catch (IOException e) { 
			    message = "No se pudo eliminar el pedido con id: " + id + "!";
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
	  }
	  
	  @GetMapping("/pedidos/{id}")
	  @ResponseBody
	  public ResponseEntity<Pedido> getPedido(@PathVariable Long id) {
		  Optional<Pedido> pedido = pedidoService.findPedido(id);
		  return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
	  }
	  
	  @PutMapping("/pedidos/resolver/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> resolver(@PathVariable Long id) {
		String message = "";
		try {
			pedidoService.resolver(id);
			message = "Se resolvió el pedido con id: " + id;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (CustomException e) {
		    return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  }
	  
	  @PutMapping("/pedidos/resolver/rechazados/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> resolverRechazados(@PathVariable Long id) {
		String message = "";
		try {
			pedidoService.resolverRechazados(id);
			message = "Se volvió a reenviar la resolución para el pedido con id: " + id;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (CustomException e) {
		    return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  }
	  
	  @PutMapping("/pedidos/notifyPayment/{id}")
	  @ResponseBody
	  // Falta verificar que el Pedido cuenta con un Presupuesto asociado (O sea, se adjunto el Presupuesto previamente)
	  public ResponseEntity<ResponseMessage> notifyPayment(@PathVariable Long id, @RequestHeader("authorization") String authorization) {
		String message = "";
		try {
			String token = authorization.split(" ")[1];
			String userName = jwtUtils.getUserNameFromJwtToken(token);
			pedidoService.notifyPayment(id, userName);
			message = "Se ha notificado exitosamente el pago para el pedido con id: " + id;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (CustomException e) {
		    return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  };
	  
	  @PutMapping("/pedidos/agreeToTheSolution")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> sendApproveSolution(@RequestBody ApproveSolution solution, @RequestHeader("authorization") String authorization) {
		String message = "";
		try {
			String token = authorization.split(" ")[1];
			String userName = jwtUtils.getUserNameFromJwtToken(token);
			String result = "desaprobado";
			if(solution.approve) {
				result = "aprobado";
			}
			pedidoService.sendApproveSolution(solution.pedido, userName); 
			message = "Se marco como" + result + " el pedido con id: " + solution.pedido.getId();
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}
		catch (CustomException e) {
			return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  };
	  
	  @PutMapping("/pedidos/revisar/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> revisar(@PathVariable Long id) {
		String message = "";
		try {
			message = pedidoService.revisar(id);
			message = "Se reviso el pedido con id: " + id + "." + message;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (CustomException e) {
		    return ResponseEntity.status(e.getHttpStatus()).body(new ResponseMessage(e.getMessage()));
		}
	  };

	public boolean dummyMethod(){
		return true;
	}
	
	

}
