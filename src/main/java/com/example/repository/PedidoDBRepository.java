package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.hibernate.annotations.Sort;
import com.example.model.Pedido;

@SuppressWarnings("deprecation")
@Repository
public interface PedidoDBRepository extends JpaRepository<Pedido, Long> {
	
	//ArrayList<Pedido> findByStateOrderByNumeroAsc(String state);
	ArrayList<Pedido> findByStateValue(String value);
	//List<Pedido> findBy;
	// findByStudent_Grades_ClassName
	//List<Pedido> findByPropietario_User_Username(final String Username);
	
	List<Pedido> findByPropietarioUsername(String Username);
	//List<Pedido> find findByPropietario(String userName);
	
	
	List<Pedido> findByPropietarioUsernameAndStateValue(String Username, String value);
	
	Integer countByPropietarioUsernameAndStateValue(String Username, String value);
}




