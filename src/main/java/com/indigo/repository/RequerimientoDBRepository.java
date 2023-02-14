package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Requerimiento;

@Repository
public interface RequerimientoDBRepository extends JpaRepository<Requerimiento, String> {

}
