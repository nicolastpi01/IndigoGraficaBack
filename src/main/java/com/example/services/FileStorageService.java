package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.model.FileDB;
import com.example.repository.FileDBRepository;
import org.springframework.util.StringUtils;
import java.util.stream.Stream;

@Service
public class FileStorageService {
	
	@Autowired
	private FileDBRepository fileDBRepository;
	
	public FileDB store(MultipartFile file) {
		try {
	    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
	    	return fileDBRepository.save(FileDB);
	    } catch (Exception e) {
	    	throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	}
	
	
	public FileDB getFile(String id) {
	  return fileDBRepository.findById(id).get();
	}
	  
	public Stream<FileDB> getAllFiles() {
	  return fileDBRepository.findAll().stream();
	}

}
