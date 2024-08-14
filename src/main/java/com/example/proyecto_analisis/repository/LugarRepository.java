package com.example.proyecto_analisis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.proyecto_analisis.models.Lugar;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {

    @Query("Select l from Lugar l where l.id_tipo_lugar = ?1")
    public List<Lugar> mostrarPaises(int id_tipo_lugar);

    @Query("Select l from Lugar l where l.id_tipo_lugar = ?1 and l.id_lugar_padre = ?2")
    public List<Lugar> mostrarLugares(int id_tipo_lugar, int id_lugar_padre);
}
