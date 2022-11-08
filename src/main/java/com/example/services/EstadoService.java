package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Estado.Estado;
import com.example.repository.EstadoDBRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoDBRepository estadoDBRepository;

	@Transactional
	public Estado save(Estado estado) {
		try {
	    	return estadoDBRepository.save(estado);
	    } catch (Exception e) {
	    	throw new RuntimeException("Could not store the state. Error: " + e.getMessage());
	    }
	}

}