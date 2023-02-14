package com.indigo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indigo.model.Color;
import com.indigo.model.Tipo;
import com.indigo.services.PedidoStorageService;
import com.indigo.services.TipoStorageService;

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
