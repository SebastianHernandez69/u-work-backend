package com.example.proyecto_analisis.models.dto;

import java.util.List;

import com.example.proyecto_analisis.models.EstadoCivil;
import com.example.proyecto_analisis.models.Genero;
import com.example.proyecto_analisis.models.Industria;
import com.example.proyecto_analisis.models.Lugar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatosGP {
    
    private List<Genero> generos;

    private List<Lugar> lugares;

    private List<EstadoCivil> estadoCivil;

    private List<Industria> industrias;

}
