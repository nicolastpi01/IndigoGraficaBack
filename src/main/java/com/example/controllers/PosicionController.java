package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controllers.model.Posicion;
import com.example.controllers.services.PosicionStorageService;

@Controller
public class PosicionController {
	
	@Autowired
	private PosicionStorageService posicionService;
	
	 @PostMapping("/posicion")
	 @ResponseBody
	 public ResponseEntity<Posicion> create(@RequestBody Posicion posicion) {
			Posicion pos = posicionService.save(posicion);
		    return ResponseEntity.status(HttpStatus.OK).body(pos);
	  }

}
