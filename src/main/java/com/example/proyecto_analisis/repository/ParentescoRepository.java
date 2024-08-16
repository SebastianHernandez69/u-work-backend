package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Parentesco;

import jakarta.transaction.Transactional;

public interface ParentescoRepository extends JpaRepository<Parentesco, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO parentescos (PARENTESCO) VALUES (:parentescoP)",nativeQuery = true )
    public void ingresarParentesco(@Param("parentescoP") String parentescoP);
}
