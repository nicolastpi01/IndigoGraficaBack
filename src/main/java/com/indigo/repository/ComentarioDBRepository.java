package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Comentario;

@Repository
public interface ComentarioDBRepository extends JpaRepository<Comentario, Long> {

}
