package com.example.proyecto_analisis.models.dto;

import java.util.List;

import com.example.proyecto_analisis.models.Contrato;
import com.example.proyecto_analisis.models.FormacionProfesional;
import com.example.proyecto_analisis.models.Idioma;
import com.example.proyecto_analisis.models.Lugar;
import com.example.proyecto_analisis.models.Modalidad;
import com.example.proyecto_analisis.models.NivelAcademico;
import com.example.proyecto_analisis.models.NivelIdioma;
import com.example.proyecto_analisis.models.Puesto;
import com.example.proyecto_analisis.models.TipoEmpleo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionsCrearOfertaDTO {
    
    private List<TipoEmpleo> tiposEmpleos;

    private List<Contrato> tiposContratos;

    private List<Lugar> paises;

    private List<FormacionProfesional> formacionesAcademicas;

    private List<NivelAcademico> nivelesAcademicos;
    
    private List<Modalidad> modalidades;

    private List<Puesto> puestos;

    private List<Idioma> idiomas;

    private List<NivelIdioma> nivelIdioma;

}
