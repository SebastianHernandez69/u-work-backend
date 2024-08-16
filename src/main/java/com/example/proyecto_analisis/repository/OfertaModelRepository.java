package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyecto_analisis.models.Oferta;

public interface OfertaModelRepository extends JpaRepository<Oferta,Integer> {
    
}
