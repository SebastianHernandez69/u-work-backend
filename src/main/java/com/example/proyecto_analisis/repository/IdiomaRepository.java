package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Idioma;

import jakarta.transaction.Transactional;

public interface IdiomaRepository extends JpaRepository<Idioma,Integer>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO idiomas (IDIOMA) VALUES (:idiomaP)",nativeQuery = true )
    public void ingresarIdioma(@Param("idiomaP") String idiomaP);
}
