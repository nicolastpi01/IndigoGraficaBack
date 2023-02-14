package com.indigo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indigo.model.Tipo;
import com.indigo.repository.TipoDBRepository;

@Service
public class TipoStorageService {
	
	@Autowired
	private TipoDBRepository tipoRepository;

	@Transactional(readOnly=true)
	public List<Tipo> getAll() {
		return tipoRepository.findAll();
	}

}
