package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.TipoEmpleo;

import jakarta.transaction.Transactional;

public interface TipoEmpleoRepository extends JpaRepository<TipoEmpleo,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipo_empleo (TIPO_EMPLEO) VALUES (:tipoEmpleop)",nativeQuery = true )
    public void ingresarTipoEmpleo(@Param("tipoEmpleop") String tipoEmpleop);
}
