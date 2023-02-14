package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Solucion;

@Repository
public interface SolucionDBRepository extends JpaRepository<Solucion, String>  {

}
