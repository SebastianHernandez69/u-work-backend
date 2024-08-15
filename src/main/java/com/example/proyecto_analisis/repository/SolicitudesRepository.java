package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.proyecto_analisis.models.Solicitante;

public interface SolicitudesRepository extends JpaRepository<Solicitante, Integer>{
    

    @Modifying
    @Transactional
    @Query(value = "update solicitudes "+
                    "set id_estado_solicitud = :idEstadoSolicitud "+
                    "where id_solicitud = :idSolicitud", nativeQuery = true)
    public void actualizarEstadoSolicitud(
    @Param(value = "idSolicitud") int idSolicitud, 
    @Param(value = "idEstadoSolicitud") int idEstadoSolicitud);

}
