package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.exception.PedidoIncorrectoException;
import com.example.security.authService.UserDetailsImpl;
import com.example.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.dto.UsuarioDTO;
import com.example.message.ResponseMessage;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.services.PedidoStorageService;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoStorageService pedidoService;

	@Autowired
	JwtUtils jwtUtils;

	/*
	@PostMapping("/pedidos")
	public ResponseEntity<ResponseMessage> altaPedido(@RequestParam("files[]") MultipartFile[] files, @RequestPart("pedido") Pedido pedido, @RequestPart("requerimientos") List<List<Requerimiento>> requerimientos) {
		String message = "";
	    try {
	      pedidoService.store(files, pedido, requerimientos);
	      message = "Se Agrego el pedido: " + pedido.getNombre();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (PedidoIncorrectoException e) {
			message = e.getMessage() + ". Pedido: " + pedido.getNombre() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "No se pudo agregar el pedido: " + pedido.getNombre() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}
	*/
	
	@PostMapping("/pedidos/create")
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido, @RequestHeader("authorization") String authorization) {
		//String message = "";
	    try {
			//busco al usuario de este token
			String token = authorization.split(" ")[1];
			String userName = jwtUtils.getUserNameFromJwtToken(token);
			
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
	  public ResponseEntity<ResponseMessage> reservar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
		String message = "";
		try {
			pedidoService.reservar(id, usuarioDTO);
			message = "Se reservo el pedido con id: " + id;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (Exception e) { // Revisar el try catch. Atomizar las excepciones.. Ver 3.3 en docu discord
		    message = "No se pudo reservar el pedido con id: " + id + "!";
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	  }
	  
	  @PutMapping("/pedidos/update")
	  @ResponseBody
	  public ResponseEntity<Pedido> update(@RequestBody Pedido pedido) {
		//String message = "";
		//try {
			Pedido pedidoActualizado = pedidoService.actualizar(pedido);
			//message = "Se actualizo el pedido con id: " + pedido.getId();
		    return ResponseEntity.status(HttpStatus.OK).body(pedidoActualizado);
		//}
		//catch (IllegalArgumentException e) { // Revisar el try catch. Atomizar las excepciones.. Ver 3.3 en docu discord
		    //message = "No se pudo actualizar el pedido con id: " + pedido.getId() + "!";
		    //return ResponseEntity.status(HttpStatus.BAD_REQUEST);
		//}
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

	public boolean dummyMethod(){
		return true;
	}

}
