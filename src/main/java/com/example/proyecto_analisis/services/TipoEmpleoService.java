package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.TipoEmpleo;
import com.example.proyecto_analisis.repository.TipoEmpleoRepository;

@Service
public class TipoEmpleoService {
    @Autowired
    private TipoEmpleoRepository tipoEmpleoRepositorio;

    public List<TipoEmpleo> mostrarTipoEmpleos(){
        return (List<TipoEmpleo>) tipoEmpleoRepositorio.findAll();
    }

    public void eliminarTipoEmpleo(int idTipoEmpleo){
        tipoEmpleoRepositorio.deleteById(idTipoEmpleo);
    }

    public void ingresarTipoEmpleo(String tipoEmpleo){
        tipoEmpleoRepositorio.ingresarTipoEmpleo(tipoEmpleo);
    }
}
