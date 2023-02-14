package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Comentario;

@Repository
public interface ComentarioDBRepository extends JpaRepository<Comentario, Long> {

}
