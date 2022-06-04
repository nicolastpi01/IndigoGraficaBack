package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.model.Posicion;
import com.example.repository.PosicionDBRepository;

@Service
public class PosicionStorageService {
	
	@Autowired
	private PosicionDBRepository posicionRepository;
	
	@Transactional
	public Posicion save(Posicion posicion) throws IllegalArgumentException {
		return posicionRepository.save(posicion);
		
	}

}
