package com.example.controllers.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.controllers.model.Tipo;
import com.example.controllers.repository.TipoDBRepository;

@Service
public class TipoStorageService {
	
	@Autowired
	private TipoDBRepository tipoRepository;

	@Transactional(readOnly=true)
	public List<Tipo> getAll() {
		return tipoRepository.findAll();
	}

}
