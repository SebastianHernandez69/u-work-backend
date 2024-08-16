package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.FormacionProfesional;
import com.example.proyecto_analisis.repository.FormacionProfRepository;

@Service
public class FormProfesionalService {

    @Autowired
    private FormacionProfRepository formacionProfRepository;

    public List<FormacionProfesional> mostrarFormacionProfesionals(){
        return (List<FormacionProfesional>) formacionProfRepository.findAll();
    }

    public void eliminarFormacionProfPorId(int idFormacion){
        formacionProfRepository.deleteById(idFormacion);
    }

    public void ingresarFormacionProf(String formacion){
        formacionProfRepository.ingresarFormacionProfesional(formacion);
    }
}
