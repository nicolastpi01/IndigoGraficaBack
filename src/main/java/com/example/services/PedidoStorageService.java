package com.example.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ColorDTO;
import com.example.dto.PedidoDTO;
import com.example.dto.UsuarioDTO;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Pedido;
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
	
	public Tipo storePedido(PedidoDTO pedidoDTO) throws IOException {
		
		Pedido pedido = pedidoDTO.toPedido();
		Tipo tipo = pedidoDTO.getTipo().toTipo();
		
		Set<Color> colores = pedidoDTO.getColores().stream().map((colorDTO -> colorDTO.toColor())).collect(Collectors.toSet());
		Set<FileDB> files = pedidoDTO.getFiles().stream().map((fileDTO -> fileDTO.toFile())).collect(Collectors.toSet());
		
		colores.forEach(color -> {
			colorRepo.save(color);
		});
		colores.forEach(color -> {
			pedido.addColor(color);
		});
		
		files.forEach((file -> {
			//file.setRequerimientos(new HashSet<>());
			pedido.addFile(file);
		}));
		
		//pedido.setFiles(new HashSet<>());
		
		tipo.addPedido(pedido);
		return tipoRepo.save(tipo);
	}

	public List<Pedido> getAllByState(String state) {
		return pedidoDBRepository.findByState(state) ;
	}

	public void reservar(String id, UsuarioDTO usuarioDTO) throws IOException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		pedido.setState("reservado");
		pedido.setEncargado(usuarioDTO.getNombre());
		pedidoDBRepository.save(pedido);
	}

}
