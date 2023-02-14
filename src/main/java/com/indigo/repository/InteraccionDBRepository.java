package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Interaccion;

@Repository
public interface InteraccionDBRepository extends JpaRepository<Interaccion, Long> {

}
