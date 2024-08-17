package com.example.proyecto_analisis.services;

import java.util.List;

import com.example.proyecto_analisis.models.EstadoCivil;

public interface EstCivilService {
    public List<EstadoCivil> mostrarEstadosCiviles();

    public void eliminarEstadoCivilPorId(int idEstadoCivilP);

    public void ingresarEstadoCivil(String estadoCivil);
}
