package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Estado.Estado;

@Repository
public interface EstadoDBRepository extends JpaRepository<Estado, Long> {

}
