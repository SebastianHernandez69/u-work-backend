package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Puesto;
import com.example.proyecto_analisis.repository.PuestoRepository;

@Service
public class PuestoService {
    @Autowired
    private PuestoRepository puestoRepositorio;

    public List<Puesto> mostrarPuestos(){
        return (List<Puesto>) puestoRepositorio.findAll();
    }

    public void eliminarPuesto(int idPuesto){
        puestoRepositorio.deleteById(idPuesto);
    }

    public void ingresarPuesto(String puesto){
        puestoRepositorio.ingresarPuesto(puesto);
    }
}
