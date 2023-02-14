package com.example.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controllers.model.Color;
import com.example.controllers.model.Tipo;
import com.example.controllers.services.PedidoStorageService;
import com.example.controllers.services.TipoStorageService;

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
