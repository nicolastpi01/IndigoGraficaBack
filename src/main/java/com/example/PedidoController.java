package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.message.ResponseMessage;
import com.example.model.Pedido;
import com.example.services.PedidoStorageService;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoStorageService pedidoService;
	
	@PostMapping("/pedidos")
	public ResponseEntity<ResponseMessage> altaPedido(@RequestBody Pedido pedido) {
		String message = "";
	    try {
	      pedidoService.storePedido(pedido);
	      message = "Uploaded the file successfully: " + pedido.getNombre();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + pedido.getNombre() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}

}
