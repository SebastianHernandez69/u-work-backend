package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Contrato;

import com.example.proyecto_analisis.repository.ContratoRepository;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepositorio;

    public List<Contrato> mostrarContratos(){
        return (List<Contrato>) contratoRepositorio.findAll();
    }

    public void eliminarContratoPorID(int idContrato){
        contratoRepositorio.deleteById(idContrato);
    }

    public void ingresarContrato(String contrato){
        contratoRepositorio.ingresarContrato(contrato);
    }
}
