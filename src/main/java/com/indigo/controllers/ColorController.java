package com.indigo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indigo.model.Color;
import com.indigo.services.ColorStorageService;

@Controller
public class ColorController {
	
	@Autowired
	private ColorStorageService colorService;
	
	@GetMapping("/colores")
	@ResponseBody
	public ResponseEntity<List<Color>> getAll() {
		List<Color> colores = colorService.getAll();
	    return ResponseEntity.ok().body(colores);
	}

}
