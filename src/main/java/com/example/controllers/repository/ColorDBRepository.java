package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.Color;

@Repository
public interface ColorDBRepository extends JpaRepository<Color, Long> {

	

}
