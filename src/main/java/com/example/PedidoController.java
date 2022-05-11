package com.example;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.PedidoDTO;
import com.example.dto.UsuarioDTO;
import com.example.message.ResponseMessage;
import com.example.model.Pedido;
import com.example.services.PedidoStorageService;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoStorageService pedidoService;

	@PostMapping("/pedidos")
	public ResponseEntity<ResponseMessage> altaPedido(@RequestParam("files[]") MultipartFile[] files, @RequestPart("pedido") Pedido pedido) {
		String message = "";
	    try {
	      pedidoService.store(files, pedido);
	      message = "Se Agrego el pedido: " + pedido.getNombre();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "No se pudo agregar el pedido: " + pedido.getNombre() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}
	
	  @GetMapping("/pedidos")
	  @ResponseBody
	  public ResponseEntity<List<Pedido>> getPedidosByState(@RequestParam String state) {
	    List<Pedido> pedidos = pedidoService.getAllByState(state);
	    //List<PedidoDTO> pedidosDTO = pedidos.stream().map(pedido -> (new PedidoDTO(pedido))).collect(Collectors.toList());
	    return ResponseEntity.ok().body(pedidos);
	  }
	  
	  @PutMapping("/pedidos/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> reservar(@PathVariable String id, @RequestBody UsuarioDTO usuarioDTO) {
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

	public boolean dummyMethod(){
		return true;
	}

}
