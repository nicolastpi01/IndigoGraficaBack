package com.indigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indigo.model.Color;

@Repository
public interface ColorDBRepository extends JpaRepository<Color, Long> {

	

}
