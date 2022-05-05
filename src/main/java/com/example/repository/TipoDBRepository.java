package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Tipo;

@Repository
public interface TipoDBRepository extends JpaRepository<Tipo, Long> {

}
