package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.EstadoCivil;
import com.example.proyecto_analisis.models.Genero;
import com.example.proyecto_analisis.models.Industria;
import com.example.proyecto_analisis.models.Lugar;
import com.example.proyecto_analisis.models.Modalidad;
import com.example.proyecto_analisis.models.Puesto;
import com.example.proyecto_analisis.models.dto.DatosGP;
import com.example.proyecto_analisis.models.dto.OptionsCrearOfertaDTO;
import com.example.proyecto_analisis.repository.ContratoRepository;
import com.example.proyecto_analisis.repository.FormacionProfRepository;
import com.example.proyecto_analisis.repository.IdiomaRepository;
import com.example.proyecto_analisis.repository.ModalidadRepository;
import com.example.proyecto_analisis.repository.NivelAcademicoRepository;
import com.example.proyecto_analisis.repository.NivelIdiomaRepository;
import com.example.proyecto_analisis.repository.PuestoRepository;
import com.example.proyecto_analisis.repository.TipoEmpleoRepository;
import com.example.proyecto_analisis.services.IdiomaService;
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
    private TipoEmpleoRepository tipoEmpleoRep;
    
    @Autowired
    private ContratoRepository contratosRep;

    @Autowired
    private FormacionProfRepository formacionProfRep;

    @Autowired
    private NivelAcademicoRepository nRepository;

    @Autowired
    private ModalidadRepository mRepository;

    @Autowired
    private PuestoRepository puestoRepository;

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Autowired
    private NivelIdiomaRepository nivelIdiomaRep;

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

    @GetMapping("/crearOferta/info")
    public ResponseEntity<OptionsCrearOfertaDTO> obtenerInfoCrearOferta() {

        OptionsCrearOfertaDTO opciones = new OptionsCrearOfertaDTO();

        opciones.setTiposEmpleos(tipoEmpleoRep.findAll());
        opciones.setTiposContratos(contratosRep.findAll());
        opciones.setPaises(lugarImpl.mostrarPaises(1));
        opciones.setFormacionesAcademicas(formacionProfRep.findAll());
        opciones.setNivelesAcademicos(nRepository.findAll());
        opciones.setModalidades(mRepository.findAll());
        opciones.setPuestos(puestoRepository.findAll());
        opciones.setIdiomas(idiomaRepository.findAll());
        opciones.setNivelIdioma(nivelIdiomaRep.findAll());


        return ResponseEntity.ok(opciones);
    }
    

}
