package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.NivelAcademico;

import jakarta.transaction.Transactional;

public interface NivelAcademicoRepository extends JpaRepository<NivelAcademico,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nivel_academico (nivel_academico) VALUES (:nivelAcadP)",nativeQuery = true )
    public void ingresarNivelAcademico(@Param("nivelAcadP") String nivelAcadP);
}
