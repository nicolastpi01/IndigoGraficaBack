package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Posicion;

@Repository
public interface PosicionDBRepository extends JpaRepository<Posicion, String> {

}
