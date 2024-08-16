package com.example.proyecto_analisis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Parentesco;
import com.example.proyecto_analisis.repository.ParentescoRepository;
import com.example.proyecto_analisis.services.ParentescoService;

@Service
public class ParentescoServiceImpl implements ParentescoService {

    @Autowired
    private ParentescoRepository parentescoRepositorio;

    @Override
    public List<Parentesco> mostrarParentescos() {
        return (List<Parentesco>) parentescoRepositorio.findAll();
    }

    @Override
    public void eliminarParentesco(int idParentesco) {
        parentescoRepositorio.deleteById(idParentesco);
    }

    @Override
    public void ingresaParentesco(String parentesco) {
        parentescoRepositorio.ingresarParentesco(parentesco);
    }
    
}
