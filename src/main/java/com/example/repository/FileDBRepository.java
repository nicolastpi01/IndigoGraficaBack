package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Long> {

}


