package com.example.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Color;
import com.example.repository.ColorDBRepository;

@Service
public class ColorStorageService {
	
	@Autowired
	private ColorDBRepository colorRepository;

	@Transactional(readOnly=true)
	public List<Color> getAll() {
		return colorRepository.findAll();
	}

}
