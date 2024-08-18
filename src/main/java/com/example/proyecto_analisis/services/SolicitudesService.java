package com.example.proyecto_analisis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.dto.SolicitudDTO;
import com.example.proyecto_analisis.repository.SolicitudesRepository;

@Service
public class SolicitudesService {
    
    @Autowired
    SolicitudesRepository solicitudesRepository;

    public void cambiarEstadoSolicitud(int idSolicitud, int idEstadoSolicitud){
        solicitudesRepository.actualizarEstadoSolicitud(idSolicitud, idEstadoSolicitud);
    }

    public void crearSolicitud(SolicitudDTO solicitud){

        solicitudesRepository
        .crearSolicitud(solicitud.getIdOferta(),
                        solicitud.getIdSolicitante(), 
                        solicitud.getIdEstadoSolicitud(),
                        solicitud.getEmisorSolicitud(),
                        solicitud.getDescripcion());
    }

    // cambiar estado solicitud
    public void aceptarRechazarSolicitudSolicitante(int idEstadoSolicitudP, int idSolicitudP, int idSolicitanteP){

            solicitudesRepository.actualizarEstadoSolicitud(idSolicitudP, idEstadoSolicitudP);
            int idOferta = solicitudesRepository.obtenerIdOfertaSolicitud(idSolicitudP, idSolicitanteP);

            solicitudesRepository.enviarNotificacionEmpresa(idSolicitanteP,idOferta,idEstadoSolicitudP,idSolicitudP);
    
    }

}
