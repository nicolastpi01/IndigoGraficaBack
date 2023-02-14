package com.indigo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indigo.model.Estado.Estado;
import com.indigo.repository.EstadoDBRepository;

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
