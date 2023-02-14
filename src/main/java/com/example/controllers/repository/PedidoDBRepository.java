package com.example.controllers.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Pedido;

import org.hibernate.annotations.Sort;

@SuppressWarnings("deprecation")
@Repository
public interface PedidoDBRepository extends JpaRepository<Pedido, Long> {

	ArrayList<Pedido> findByStateValue(String value);
	
	Integer countByStateValue(String value);
	
	ArrayList<Pedido> findByPropietarioUsername(String Username);

	List<Pedido> findByPropietarioUsernameAndStateValue(String Username, String value);

	Integer countByPropietarioUsername(String userName);
	
	Integer countByPropietarioUsernameAndStateValue(String Username, String value);

	Integer countByEncargadoUsernameAndStateValue(String Username, String value);
}




