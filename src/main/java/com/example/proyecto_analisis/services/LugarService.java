package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Lugar;
import com.example.proyecto_analisis.repository.LugarRepository;

@Service
public class LugarService {
    
    @Autowired
    private LugarRepository lugarRepositorio;

    public List<Lugar> mostrarPaises(int id_tipo_lugar){
        return lugarRepositorio.mostrarPaises(id_tipo_lugar);
    }

    public List<Lugar> mostrarLugares(int id_tipo_lugar, int id_lugar_padre){
        return lugarRepositorio.mostrarLugares(id_tipo_lugar,id_lugar_padre);
    }
}
