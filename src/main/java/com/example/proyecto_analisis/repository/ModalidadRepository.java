package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Modalidad;

import jakarta.transaction.Transactional;

public interface ModalidadRepository extends JpaRepository<Modalidad,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO modalidad (modalidad) VALUES (:modalidadP)",nativeQuery = true )
    public void ingresarModalidad(@Param("modalidadP") String modalidadP);
}
