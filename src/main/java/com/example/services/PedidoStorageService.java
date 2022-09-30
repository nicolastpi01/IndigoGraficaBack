package com.example.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.exception.PedidoIncorrectoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.dto.UsuarioDTO;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.repository.PedidoDBRepository;
import com.example.repository.PosicionDBRepository;

@Service
public class PedidoStorageService {
	
	@Autowired
	private PedidoDBRepository pedidoDBRepository;
	@Autowired
	private PosicionDBRepository posDBRepository;
	
	@Transactional
	public Pedido store(MultipartFile[] files, Pedido pedido, List<List<Requerimiento>> requerimientos) throws PedidoIncorrectoException {
		
		List<FileDB> filesDB = new ArrayList<>();
		pedido.validar();
		for(int index = 0;index < files.length; index++) {
			try {
				MultipartFile file = Arrays.asList(files).get(index);
				FileDB FileDB = new FileDB(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes());
				if(!requerimientos.isEmpty()) {
					if(requerimientos.get(index) != null) {
						//FileDB.setRequerimientos(requerimientos.get(index).stream().collect(Collectors.toSet()));
						FileDB.setRequerimientos(requerimientos.get(index).stream().collect(Collectors.toList()));
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

	@Transactional(readOnly=true)
	public List<Pedido> getAllByPropietario(String userName) {
		return pedidoDBRepository.findByPropietario(userName);
	}

	@Transactional
	public void reservar(String id, UsuarioDTO usuarioDTO) throws IOException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		pedido.setState("reservado");
		pedido.setEncargado(usuarioDTO.getNombre());
		pedidoDBRepository.save(pedido);
	}

	@Transactional
	public Pedido actualizar(Pedido pedido) throws IllegalArgumentException {
		return pedidoDBRepository.save(pedido);
	}

	@Transactional
	public Pedido create(Pedido pedido)  throws IllegalArgumentException {
		return pedidoDBRepository.save(pedido);
	}

	@Transactional
	public void delete(String id) throws IOException {
		pedidoDBRepository.deleteById(id);
	}

	@Transactional(readOnly=true)
	public Optional<Pedido> findPedido(String id) {
		return pedidoDBRepository.findById(id);
	}

}
