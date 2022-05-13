package com.example.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Tipo;
import com.example.repository.TipoDBRepository;

@Service
public class TipoStorageService {
	
	@Autowired
	private TipoDBRepository tipoRepository;

	public List<Tipo> getAll() {
		return tipoRepository.findAll();
	}

}
