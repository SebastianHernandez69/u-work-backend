package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.TipoSeguro;
import com.example.proyecto_analisis.repository.TipoSeguroRepository;

@Service
public class TipoSeguroService {
    @Autowired
    private TipoSeguroRepository tipoSeguroRepositorio;

    public List<TipoSeguro> mostrarTipoSeguros(){
        return (List<TipoSeguro>) tipoSeguroRepositorio.findAll();
    }

    public void eliminarTipoSeguroPorId(int idTipoSeguro){
        tipoSeguroRepositorio.deleteById(idTipoSeguro);
    }

    public void ingresarTipoSeguro(String tipoSeguroP){
        tipoSeguroRepositorio.ingresarTipoSeguro(tipoSeguroP);
    }
}
