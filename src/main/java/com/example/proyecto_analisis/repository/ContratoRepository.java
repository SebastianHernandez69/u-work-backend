package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Contrato;

import jakarta.transaction.Transactional;

public interface ContratoRepository extends JpaRepository<Contrato,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO contratos (CONTRATO) VALUES (:contratoP)",nativeQuery = true )
    public void ingresarContrato(@Param("contratoP") String contratoP);
}
