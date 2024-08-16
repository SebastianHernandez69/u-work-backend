package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.FormacionProfesional;

import jakarta.transaction.Transactional;

public interface FormacionProfRepository extends JpaRepository<FormacionProfesional,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO formacion_profesional (formacion_profesional) VALUES (:formProfeP)",nativeQuery = true )
    public void ingresarFormacionProfesional(@Param("formProfeP") String formProfeP);
}
