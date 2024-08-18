package com.example.proyecto_analisis.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.repository.NotificacionEmpresaRepository;

@Service
public class NotificacionEmpresaService {
    
    @Autowired
    private NotificacionEmpresaRepository notificacionEmpresaRepositorio;

    public Map<String,Object> obtenerNotificacionEmpresa(int idNotificacion){

        Object[] objNotificacion = notificacionEmpresaRepositorio.obtenerNotificacionEmpresa(idNotificacion);

        if (objNotificacion[0] instanceof Object[]) {
            Object[] data = (Object[]) objNotificacion[0];
            Map<String,Object> notificacion = new HashMap<>();
            notificacion.put("idNotificacion", data[0]);
            notificacion.put("titulo", data[1]);
            notificacion.put("urlLogoEmpresa", data[2]);
            notificacion.put("nombreEmpresa", data[3]);
            notificacion.put("descripcion", data[4]);
            notificacion.put("fechaEnvio", data[5]);
            notificacion.put("idSolicitud", data[6]);

            return notificacion;
         
        }else{
            return Collections.emptyMap();
        }
        


        
    }

}
