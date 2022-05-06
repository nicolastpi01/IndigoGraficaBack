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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.PedidoDTO;
import com.example.message.ResponseMessage;
import com.example.model.Pedido;
import com.example.services.PedidoStorageService;
import com.example.utils.Helper;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoStorageService pedidoService;
	private Helper helper = new Helper();
	
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
	
	  @GetMapping("/pedidos")
	  @ResponseBody
	  public ResponseEntity<List<Pedido>> getPedidosByState(@RequestParam String state) {
	    List<Pedido> pedidos = pedidoService.getAllByState(state);
	    pedidos = pedidos.stream().map(pedido -> helper.pedidoFromDTO(new PedidoDTO(pedido))).collect(Collectors.toList());
	    return ResponseEntity.ok().body(pedidos);
	  }

}
