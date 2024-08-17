package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.TipoSeguro;

import jakarta.transaction.Transactional;

public interface TipoSeguroRepository extends JpaRepository<TipoSeguro,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipo_seguros (TIPO_SEGURO) VALUES (:tipoSeguroP)",nativeQuery = true )
    public void ingresarTipoSeguro(@Param("tipoSeguroP") String tipoSeguroP);
}
