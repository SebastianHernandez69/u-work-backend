package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.EstadoCivil;
import com.example.proyecto_analisis.models.Genero;
import com.example.proyecto_analisis.models.Industria;
import com.example.proyecto_analisis.models.Lugar;
import com.example.proyecto_analisis.models.dto.DatosGP;
import com.example.proyecto_analisis.services.IndustriaService;
import com.example.proyecto_analisis.services.LugarService;
import com.example.proyecto_analisis.services.impl.EstCivilServiceImpl;
import com.example.proyecto_analisis.services.impl.GeneroServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class DatosGPController {
    
    @Autowired
    private EstCivilServiceImpl estCivilImpl;
    
    @Autowired
    private GeneroServiceImpl generoImpl;

    @Autowired
    private LugarService lugarImpl;

    @Autowired 
    private IndustriaService industriaImpl;

    @GetMapping("/SIR/info")
    public ResponseEntity<DatosGP> obtenerInfoRegistro() {
        List<Genero> generos = generoImpl.mostrarGeneros();
        List<Lugar> paises = lugarImpl.mostrarPaises(1);
        List<EstadoCivil> estadoCivils = estCivilImpl.mostrarEstadosCiviles();
        List<Industria> industrias = industriaImpl.mostrarIndustrias();

        DatosGP res = new DatosGP();

        res.setGeneros(generos);
        res.setLugares(paises);
        res.setEstadoCivil(estadoCivils);
        res.setIndustrias(industrias);

        return ResponseEntity.ok(res);
    }
    

}
