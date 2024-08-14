package com.example.proyecto_analisis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Empresa;
import com.example.proyecto_analisis.repository.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepositorio;

    public void ingresarEmpresa(Empresa empresa){
        empresaRepositorio.save(empresa);
    }

    public int autenticarDirectorEmpresa(String correo, String contrasena){
        Empresa empresa = empresaRepositorio.autenticarDirectorEmpresa(correo, contrasena);

        if (empresa == null) {
            return 0;
        } else {
            return empresa.getIdDirector();
        }
    }
}
