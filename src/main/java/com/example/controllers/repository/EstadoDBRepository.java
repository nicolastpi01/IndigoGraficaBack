package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Estado.Estado;

@Repository
public interface EstadoDBRepository extends JpaRepository<Estado, Long> {

}
