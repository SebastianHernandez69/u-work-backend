package com.example.proyecto_analisis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Lugar;

import jakarta.transaction.Transactional;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {

    @Query("Select l from Lugar l where l.id_tipo_lugar = ?1")
    public List<Lugar> mostrarPaises(int id_tipo_lugar);

    @Query("Select l from Lugar l where l.id_tipo_lugar = ?1 and l.id_lugar_padre = ?2")
    public List<Lugar> mostrarLugares(int id_tipo_lugar, int id_lugar_padre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lugares(NOMBRE_LUGAR, ID_TIPO_LUGAR, ID_LUGAR_PADRE)"+
                    "VALUES (:nombreLugarP,:idTipoLugarP,:idLugarPadreP)",
                    nativeQuery = true )
    public void ingresarLugar(@Param("nombreLugarP") String nombreLugarP,@Param("idTipoLugarP") int idTipoLugarP,@Param("idLugarPadreP") int idLugarPadreP);
}
