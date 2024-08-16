package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.TipoLugar;

import jakarta.transaction.Transactional;

public interface TipoLugarRepository extends JpaRepository<TipoLugar,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipo_lugar (TIPO_LUGAR) VALUES (:tipoLugarP)",nativeQuery = true )
    public void ingresarTipoLugar(@Param("tipoLugarP") String tipoLugarP);
}
