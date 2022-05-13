package com.example;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.model.Color;
import com.example.model.Tipo;
import com.example.services.PedidoStorageService;
import com.example.services.TipoStorageService;

@Controller
public class TipoController {
	
	@Autowired
	private TipoStorageService tipoService;
	
	
	@GetMapping("/tipos")
	@ResponseBody
	public ResponseEntity<List<Tipo>> getAll() {
		List<Tipo> tipos = tipoService.getAll();
	    return ResponseEntity.ok().body(tipos);
	}

}
