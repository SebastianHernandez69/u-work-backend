package com.example.proyecto_analisis.repository;

import java.util.List;

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

    @Query(value = "SELECT CONCAT(COUNT(*)) as numTablas "+
                    "FROM information_schema.tables "+
                    "WHERE table_schema = 'mydb';", nativeQuery = true)
    public String obtenerTablas();

    @Query(value = "SELECT table_name "+
                    "FROM information_schema.tables "+
                    "WHERE table_schema = 'mydb';", nativeQuery = true)
    public List<String> nomTablas();
    
}
