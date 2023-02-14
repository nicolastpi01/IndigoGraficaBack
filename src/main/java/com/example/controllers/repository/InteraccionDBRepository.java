package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Interaccion;

@Repository
public interface InteraccionDBRepository extends JpaRepository<Interaccion, Long> {

}
