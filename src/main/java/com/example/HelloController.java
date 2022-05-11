package com.example;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.message.ResponseMessage;
import com.example.services.FileStorageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
	
	@Autowired
	private FileStorageService storageService;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> index(@RequestParam("files[]") MultipartFile[] files) {
		String message = "";
	    try {
	    	
	    	List<String> fileNames = new ArrayList<>();
	    	Arrays.asList(files).stream().forEach(file -> {
	          storageService.store(file);
	          fileNames.add(file.getOriginalFilename());
	      });
	      //storageService.store(file);

	      message = "Uploaded the file successfully: " + files;
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	    	message = "Fail to upload files!";
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}
	
	
	

}
