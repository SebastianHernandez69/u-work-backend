package com.example.proyecto_analisis.services;

import java.util.List;

import com.example.proyecto_analisis.models.Parentesco;

public interface ParentescoService {
    
    public List<Parentesco> mostrarParentescos();

    public void eliminarParentesco(int idParentesco);

    public void ingresaParentesco(String parentesco);

}
