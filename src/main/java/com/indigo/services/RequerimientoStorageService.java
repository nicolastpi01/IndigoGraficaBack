package com.indigo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indigo.repository.RequerimientoDBRepository;

@Service
public class RequerimientoStorageService {
	
	@Autowired
	private RequerimientoDBRepository requerimientoDBRepository;
	
	
	@Transactional
	public void deleteById(String id) throws IllegalArgumentException {
		requerimientoDBRepository.deleteById(id);
	}

}
