package com.indigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indigo.message.ResponseMessage;
import com.indigo.services.RequerimientoStorageService;

@Controller
public class RequerimientoControlller {
	
	@Autowired
	private RequerimientoStorageService requerimientoService;
	
	/*
	 @DeleteMapping("/requerimientos/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> delete(@PathVariable String id) {
		String message = "";
		try {
			requerimientoService.deleteById(id);
			message = "Se elimino el requerimiento con id: " + id;
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
		catch (IllegalArgumentException e) { // Revisar el try catch. Atomizar las excepciones.. Ver 3.3 en docu discord
		    message = "No se pudo eliminar el requerimiento con id: " + id + "!";
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	  }
	 */
	 

}
