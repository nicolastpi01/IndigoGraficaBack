package com.example.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controllers.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Long> {

}


