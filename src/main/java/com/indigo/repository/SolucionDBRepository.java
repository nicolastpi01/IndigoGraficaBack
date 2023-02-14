package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Solucion;

@Repository
public interface SolucionDBRepository extends JpaRepository<Solucion, String>  {

}
