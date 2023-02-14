package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Tipo;

@Repository
public interface TipoDBRepository extends JpaRepository<Tipo, Long> {

}
