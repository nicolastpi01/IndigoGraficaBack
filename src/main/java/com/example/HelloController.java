package com.example;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.message.ResponseMessage;
import com.example.model.FileDB;
import com.example.services.FileStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
	
	@Autowired
	private FileStorageService storageService;
	//private String api = "files";
	
	@PostMapping("files/upload")
	public ResponseEntity<FileDB> index(@RequestParam("file") MultipartFile file) {
		FileDB fileRet = null;
	    try {
	    	
	    	List<String> fileNames = new ArrayList<>();
	    	//Arrays.asList(files).stream().forEach(file -> {
	        fileRet = storageService.store(file);
	        fileNames.add(file.getOriginalFilename());
	      //});
	      //storageService.store(file);

	      //message = "Uploaded the files successfully: " + fileRet.getName();
	      return ResponseEntity.status(HttpStatus.OK).body(fileRet);
	    } catch (Exception e) {
	    	//message = "Fail to upload files!";
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(fileRet);
	    }
	}
	
	@PutMapping("/files/update")
	@ResponseBody
	public FileDB update(@RequestBody FileDB file) throws IllegalArgumentException {
		return storageService.update(file);
	}
	
	@DeleteMapping("/files/{id}")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> eliminar(@PathVariable Long id) {
		  String message = "";
			try {
				 storageService.delete(id);
				message = "Se elimino el archivo con id: " + id;
			    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}
			catch (IOException e) { 
			    message = "No se pudo eliminar el archivo con id: " + id + "!";
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
	 }
	
	
	

}
