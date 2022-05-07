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
import com.example.model.Color;
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
		
		Set<Color> colores = pedidoDTO.getColores().stream().map((colorDTO -> colorDTO.toColor())).collect(Collectors.toSet());
		
		colores.forEach(color -> {
			colorRepo.save(color);
			//pedido.addColor(color);
		});
		
		colores.forEach(color -> {
			//colorRepo.save(color);
			pedido.addColor(color);
		});
		
		//pedido.setColores(colores);
		
		//Tipo tipo = new Tipo("Logo", 60, 60, "sans serif");
		//Tipo tipo = pedidoDTO.getTipo()
		
		pedido.setFiles(new HashSet<>());
		
		//pedido.setColores(new HashSet<>());
		
		Tipo tipo = pedidoDTO.getTipo().toTipo();
		tipo.addPedido(pedido);
		
		return tipoRepo.save(tipo);
	}

	public List<Pedido> getAllByState(String state) {
		return pedidoDBRepository.findByState(state);
	}

}
