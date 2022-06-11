package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Pedido;

@Repository
public interface PedidoDBRepository extends JpaRepository<Pedido, String> {
	
	List<Pedido> findByState(String state);
	List<Pedido> findByPropietario(String userName);
}




