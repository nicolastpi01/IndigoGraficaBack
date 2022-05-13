package com.example.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ColorDTO;
import com.example.dto.PedidoDTO;
import com.example.dto.UsuarioDTO;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.model.Tipo;
import com.example.repository.ColorDBRepository;
import com.example.repository.PedidoDBRepository;
import com.example.repository.TipoDBRepository;

@Service
public class PedidoStorageService {
	
	@Autowired
	private PedidoDBRepository pedidoDBRepository;
	@Autowired
	private TipoDBRepository tipoRepo;
	@Autowired
	private ColorDBRepository colorRepo;
	
	@Transactional
	public Pedido store(MultipartFile[] files, Pedido pedido, List<List<Requerimiento>> requerimientos) {
		
		Set<FileDB> filesDB = new HashSet<>();
		//int index = 0;	
		/*
    	Arrays.asList(files).stream().forEach((file) -> {
    		int index = 0;	
    		try {
				FileDB FileDB = new FileDB(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes());
				if(!requerimientos.isEmpty()) {
					if(requerimientos.get(index) != null) {
						FileDB.setRequerimientos(requerimientos.get(index).stream().collect(Collectors.toSet()));
						System.out.println("Index: " + index); // El problema es el Index que esta las dos veces (Dos Files) en cero!
						index= index + 1;
					}
				}
				filesDB.add(FileDB);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
      });
      */
		for(int index = 0;index < files.length; index++) {
			try {
				MultipartFile file = Arrays.asList(files).get(index);
				FileDB FileDB = new FileDB(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes());
				if(!requerimientos.isEmpty()) {
					if(requerimientos.get(index) != null) {
						FileDB.setRequerimientos(requerimientos.get(index).stream().collect(Collectors.toSet()));
						System.out.println("Index: " + index); 
					}
				}
				filesDB.add(FileDB);
			} catch (IOException e) {
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
		}
    	
    	pedido.setFiles(filesDB);
    	return pedidoDBRepository.save(pedido);
	}

	@Transactional(readOnly=true)
	public List<Pedido> getAllByState(String state) {
		return pedidoDBRepository.findByState(state) ;
	}

	@Transactional
	public void reservar(String id, UsuarioDTO usuarioDTO) throws IOException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		pedido.setState("reservado");
		pedido.setEncargado(usuarioDTO.getNombre());
		pedidoDBRepository.save(pedido);
	}

}
