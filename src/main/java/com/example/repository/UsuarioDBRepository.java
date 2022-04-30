package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Pedido;
import com.example.model.Usuario;

@Repository
public interface UsuarioDBRepository  extends JpaRepository<Usuario, Long> {

}
