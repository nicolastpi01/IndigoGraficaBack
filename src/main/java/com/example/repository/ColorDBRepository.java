package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Color;

@Repository
public interface ColorDBRepository extends JpaRepository<Color, Long> {

	

}
