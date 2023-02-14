package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Requerimiento;

@Repository
public interface RequerimientoDBRepository extends JpaRepository<Requerimiento, String> {

}
