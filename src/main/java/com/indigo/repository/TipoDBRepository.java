package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Tipo;

@Repository
public interface TipoDBRepository extends JpaRepository<Tipo, Long> {

}
