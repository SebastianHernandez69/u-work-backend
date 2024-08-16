package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.CondicionMedica;

import jakarta.transaction.Transactional;

public interface CondicionMedicaRepository extends JpaRepository<CondicionMedica,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO condiciones_medicas (CONDICION_MEDICAS) VALUES (:condicionMedicaP)",nativeQuery = true )
    public void ingresarCondicionMedica(@Param("condicionMedicaP") String condicionMedicaP);
}
