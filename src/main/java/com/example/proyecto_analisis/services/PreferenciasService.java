package com.example.proyecto_analisis.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.dto.PreferenciasUsuarioDTO;
import com.example.proyecto_analisis.repository.PuestoRepository;

import jakarta.transaction.Transactional;

@Service
public class PreferenciasService {
    
    @Autowired
    private PuestoRepository puestoRepositorio;

    public PreferenciasUsuarioDTO obtenerPerferencias(int idPersona){

        List<Object[]> objPuestos = puestoRepositorio.obtenerPreferenciaPuestos(idPersona);
        List<Object[]> objModalidades = puestoRepositorio.obtenerPreferenciaModalidad(idPersona);
        List<Object[]> objContratos = puestoRepositorio.obtenerPreferenciaContrato(idPersona);

        List<Map<String, Object>> puestos = objPuestos.stream()
            .map(obj -> {
                Map<String,Object> map = new LinkedHashMap<>();
                    map.put("id", obj[0]);
                    map.put("puesto", obj[1]);
                    return map;
            })
        .collect(Collectors.toList());

        List<Map<String, Object>> modalidades = objModalidades.stream()
            .map(obj -> {
                Map<String,Object> map = new LinkedHashMap<>();
                    map.put("id", obj[0]);
                    map.put("modalidad", obj[1]);
                    return map;
            })
        .collect(Collectors.toList());

        List<Map<String, Object>> contratos = objContratos.stream()
            .map(obj -> {
                Map<String,Object> map = new LinkedHashMap<>();
                    map.put("id", obj[0]);
                    map.put("contrato", obj[1]);
                    return map;
            })
        .collect(Collectors.toList());

        PreferenciasUsuarioDTO preferencias = new PreferenciasUsuarioDTO();
        
        preferencias.setPuestos(puestos);
        preferencias.setModalidades(modalidades);
        preferencias.setContratos(contratos);

        return preferencias;
    }

    @Transactional
    public void eliminarPreferenciasUsuario(int idPersona){

        puestoRepositorio.eliminarPreferenciasUsuario(idPersona);

    }

}
