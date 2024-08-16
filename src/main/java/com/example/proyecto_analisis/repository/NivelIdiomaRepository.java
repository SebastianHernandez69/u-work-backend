package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.NivelIdioma;

import jakarta.transaction.Transactional;

public interface NivelIdiomaRepository extends JpaRepository<NivelIdioma,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nivel_idioma (NIVEL_IDIOMA) VALUES (:nivelIdiomaP)",nativeQuery = true )
    public void ingresarNivelIdioma(@Param("nivelIdiomaP") String nivelIdiomaP);
}
