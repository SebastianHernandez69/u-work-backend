package com.example.proyecto_analisis.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.TipoLugar;
import com.example.proyecto_analisis.repository.TipoLugarRepository;

@Service
public class TipoLugarService {
    @Autowired
    private TipoLugarRepository tipoLugarRepositorio;

    public List<TipoLugar> mostrarTipoLugar(){
        return (List<TipoLugar>) tipoLugarRepositorio.findAll();
    }

    public void eliminarTipoLugar(int idTipoLugar){
        tipoLugarRepositorio.deleteById(idTipoLugar);
    }

    public void ingresarTipoLugar(String tipoLugarP){
        tipoLugarRepositorio.ingresarTipoLugar(tipoLugarP);
    }
}
