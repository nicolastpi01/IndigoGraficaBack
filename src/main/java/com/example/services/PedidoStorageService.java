package com.example.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Pedido;
import com.example.repository.PedidoDBRepository;

@Service
public class PedidoStorageService {
	
	@Autowired
	private PedidoDBRepository pedidoDBRepository;
	
	public Pedido storePedido(Pedido pedido) throws IOException {
		  return pedidoDBRepository.save(pedido);
	}

}
