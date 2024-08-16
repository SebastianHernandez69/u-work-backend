package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.EstadoCivil;

import jakarta.transaction.Transactional;

public interface EstadoCivilRespository extends JpaRepository<EstadoCivil, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO estado_civil (ESTADO_CIVIL) VALUES (:estCivilP)",nativeQuery = true )
    public void ingresarEstadoCivil(@Param("estCivilP") String estCivilP);

}
