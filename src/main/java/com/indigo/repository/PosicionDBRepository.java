package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Posicion;

@Repository
public interface PosicionDBRepository extends JpaRepository<Posicion, String> {

}
