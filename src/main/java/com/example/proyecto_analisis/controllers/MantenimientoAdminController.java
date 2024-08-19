package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.CondicionMedica;
import com.example.proyecto_analisis.models.Contrato;
import com.example.proyecto_analisis.models.EstadoCivil;
import com.example.proyecto_analisis.models.FormacionProfesional;
import com.example.proyecto_analisis.models.Genero;
import com.example.proyecto_analisis.models.Idioma;
import com.example.proyecto_analisis.models.Industria;
import com.example.proyecto_analisis.models.Lugar;
import com.example.proyecto_analisis.models.Modalidad;
import com.example.proyecto_analisis.models.NivelAcademico;
import com.example.proyecto_analisis.models.NivelIdioma;
import com.example.proyecto_analisis.models.Parentesco;
import com.example.proyecto_analisis.models.Puesto;
import com.example.proyecto_analisis.models.TipoEmpleo;
import com.example.proyecto_analisis.models.TipoLugar;
import com.example.proyecto_analisis.models.TipoSeguro;
import com.example.proyecto_analisis.services.CondicionMedicaService;
import com.example.proyecto_analisis.services.ContratoService;
import com.example.proyecto_analisis.services.FormProfesionalService;
import com.example.proyecto_analisis.services.IdiomaService;
import com.example.proyecto_analisis.services.IndustriaService;
import com.example.proyecto_analisis.services.LugarService;
import com.example.proyecto_analisis.services.ModalidadService;
import com.example.proyecto_analisis.services.NivelAcademicoService;
import com.example.proyecto_analisis.services.NivelIdiomaService;
import com.example.proyecto_analisis.services.PuestoService;
import com.example.proyecto_analisis.services.TipoEmpleoService;
import com.example.proyecto_analisis.services.TipoLugarService;
import com.example.proyecto_analisis.services.TipoSeguroService;
import com.example.proyecto_analisis.services.impl.EstCivilServiceImpl;
import com.example.proyecto_analisis.services.impl.GeneroServiceImpl;
import com.example.proyecto_analisis.services.impl.ParentescoServiceImpl;

@RestController
@RequestMapping("/api/tablas/mantenimiento/admin")
public class MantenimientoAdminController {
    
    //===================================================================
    //tipo_empleado
    @Autowired
    private TipoEmpleoService tipoEmpleoImpl;

    @GetMapping("/tipo-empleo/mostrar")
    public List<TipoEmpleo> mostrarTipoEmpleos() {
        return (List<TipoEmpleo>) tipoEmpleoImpl.mostrarTipoEmpleos();
    }

    @PostMapping("/tipo-empleo/ingresar/{tipoEmpleoP}")
    public ResponseEntity<String> ingresarTipoEmpleo(@PathVariable String tipoEmpleoP){
        try {
            tipoEmpleoImpl.ingresarTipoEmpleo(tipoEmpleoP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/tipo-empleo/eliminar/{idTipoEmpleoP}")
    public ResponseEntity<String> eliminarTipoEmpleo(@PathVariable int idTipoEmpleoP){
        try {
            tipoEmpleoImpl.eliminarTipoEmpleo(idTipoEmpleoP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

//===================================================================
    //tipo_empleado
    @Autowired
    private ParentescoServiceImpl parentescoServiceImpl;

    @GetMapping("/parentesco/mostrar")
    public List<Parentesco> mostrarParentescos() {
        return (List<Parentesco>) parentescoServiceImpl.mostrarParentescos();
    }

    @PostMapping("/parentesco/ingresar/{parentesco}")
    public ResponseEntity<String> ingresaParentesco(@PathVariable String parentesco){
        try {
            parentescoServiceImpl.ingresaParentesco(parentesco);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/parentesco/eliminar/{idParentesco}")
    public ResponseEntity<String> eliminarParentesco(@PathVariable int idParentesco){
        try {
            parentescoServiceImpl.eliminarParentesco(idParentesco);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    
//===================================================================
    //Puestos
    @Autowired
    private PuestoService puestoService;

    @GetMapping("/puesto/mostrar")
    public List<Puesto> mostrarPuestos() {
        return (List<Puesto>) puestoService.mostrarPuestos();
    }

    @PostMapping("/puesto/ingresar/{puesto}")
    public ResponseEntity<String> ingresarPuesto(@PathVariable String puesto){
        try {
            puestoService.ingresarPuesto(puesto);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/puesto/eliminar/{idPuesto}")
    public ResponseEntity<String> eliminarPuesto(@PathVariable int idPuesto){
        try {
            puestoService.eliminarPuesto(idPuesto);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //===================================================================
    //Nivel_academico
    @Autowired
    private NivelAcademicoService nivelAcademicoService;

    @GetMapping("/nivel-academico/mostrar")
    public List<NivelAcademico> mostrarNivelAcademicos() {
        return (List<NivelAcademico>) nivelAcademicoService.mostrarNivelesAcademicos();
    }

    @PostMapping("/nivel-academico/ingresar/{nivelAcademico}")
    public ResponseEntity<String> ingresarNivelAcademico(@PathVariable String nivelAcademico){
        try {
            nivelAcademicoService.ingresarNivelAcademico(nivelAcademico);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/nivel-academico/eliminar/{idNivelAcademico}")
    public ResponseEntity<String> eliminarNivelAcademicoPorId(@PathVariable int idNivelAcademico){
        try {
            nivelAcademicoService.eliminarNivelAcademicoPorId(idNivelAcademico);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //===================================================================
    //Tipo_lugar
    @Autowired
    private TipoLugarService tipoLugarService;

    @GetMapping("/tipo-lugar/mostrar")
    public List<TipoLugar> mostrarTipoLugars() {
        return (List<TipoLugar>) tipoLugarService.mostrarTipoLugar();
    }

    @PostMapping("/tipo-lugar/ingresar/{tipoLugar}")
    public ResponseEntity<String> ingresarTipoLugar(@PathVariable String tipoLugar){
        try {
            tipoLugarService.ingresarTipoLugar(tipoLugar);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/tipo-lugar/eliminar/{idTipoLugar}")
    public ResponseEntity<String> eliminarTipoLugar(@PathVariable int idTipoLugar){
        try {
            tipoLugarService.eliminarTipoLugar(idTipoLugar);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //===================================================================
    //Lugares
    @Autowired
    private LugarService lugarService;

    @GetMapping("/lugar/mostrar")
    public List<Lugar> mostrarLugares() {
        return (List<Lugar>) lugarService.mostrarLugaresTodos();
    }

    @PostMapping("/lugar/ingresar/{lugarP}/{idTIpoLugarP}/{idLugarPadreP}")
    public ResponseEntity<String> ingresarLugar(@PathVariable String lugarP, @PathVariable int idTIpoLugarP, @PathVariable int idLugarPadreP){
        try {
            lugarService.ingresarLugar(lugarP,idTIpoLugarP,idLugarPadreP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/lugar/eliminar/{idLugarP}")
    public ResponseEntity<String> eliminarLugar(@PathVariable int idLugarP){
        try {
            lugarService.eliminarLugar(idLugarP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //=============================================================
    @Autowired
    public IndustriaService industriaImpl;

    @GetMapping("/industria/mostrar")
    public List<Industria> mostrarIndustrias() {
        return (List<Industria>) industriaImpl.mostrarIndustrias();
    }

    @PostMapping("/industria/ingresar/{industriaP}")
    public ResponseEntity<String> ingresarIndustria(@PathVariable String industriaP){
        try {
            industriaImpl.ingresarIndustria(industriaP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/industria/eliminar/{idIndustriaP}")
    public ResponseEntity<String> eliminarIndustriaPorId(@PathVariable int idIndustriaP){
        try {
            industriaImpl.eliminarIndustriaPorId(idIndustriaP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //===================================================
    @Autowired
    private TipoSeguroService tipoSeguroImpl;

    @GetMapping("/tipo-seguro/mostrar")
    public List<TipoSeguro> mostrarTipoSeguros() {
        return (List<TipoSeguro>) tipoSeguroImpl.mostrarTipoSeguros();
    }

    @PostMapping("/tipo-seguro/ingresar/{tipoSeguroP}")
    public ResponseEntity<String> ingresarTipoSeguro(@PathVariable String tipoSeguroP){
        try {
            tipoSeguroImpl.ingresarTipoSeguro(tipoSeguroP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/tipo-seguro/eliminar/{idTipoSeguroP}")
    public ResponseEntity<String> eliminarTipoSeguro(@PathVariable int idTipoSeguroP){
        try {
            tipoSeguroImpl.eliminarTipoSeguroPorId(idTipoSeguroP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //=============================================================
    @Autowired
    private EstCivilServiceImpl estCivilServiceImpl;

    @GetMapping("/estado-civil/mostrar")
    public List<EstadoCivil> mostrarEstadosCiviles() {
        return (List<EstadoCivil>) estCivilServiceImpl.mostrarEstadosCiviles();
    }

    @PostMapping("/estado-civil/ingresar/{estadoCivilP}")
    public ResponseEntity<String> ingresarEstadoCivil(@PathVariable String estadoCivilP){
        try {
            estCivilServiceImpl.ingresarEstadoCivil(estadoCivilP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/estado-civil/eliminar/{idEstadoCivil}")
    public ResponseEntity<String> eliminarEstadoCivil(@PathVariable int idEstadoCivil){
        try {
            estCivilServiceImpl.eliminarEstadoCivilPorId(idEstadoCivil);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //====================================================
    @Autowired
    private CondicionMedicaService condicionMedicaImpl;

    @GetMapping("/condicion-medica/mostrar")
    public List<CondicionMedica> mostrarCondicionMed() {
        return (List<CondicionMedica>) condicionMedicaImpl.mostrarCondicionMed();
    }

    @PostMapping("/condicion-medica/ingresar/{condicionMedP}")
    public ResponseEntity<String> ingresarCondicionMedica(@PathVariable String condicionMedP){
        try {
            condicionMedicaImpl.ingresarCondicionMedica(condicionMedP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/condicion-medica/eliminar/{idCondicionMedP}")
    public ResponseEntity<String> eliminarCondicionMed(@PathVariable int idCondicionMedP){
        try {
            condicionMedicaImpl.eliminarCondicionMedPorId(idCondicionMedP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("/tabs")
    public ResponseEntity<String> obtenerTablas(){
        try {
            String tablas = condicionMedicaImpl.obtenerTablas();

            return ResponseEntity.ok(tablas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: ");
        }
    }

    @GetMapping("/nomTabs")
    public ResponseEntity<List<String>> nomTablas(){
        try {
            List<String> tabs = condicionMedicaImpl.nomTablas();
            return ResponseEntity.ok(tabs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //=================================================================
    @Autowired
    private IdiomaService idiomaImpl;

    @GetMapping("/idioma/mostrar")
    public List<Idioma> mostrarIdiomas() {
        return (List<Idioma>) idiomaImpl.mostrarIdiomas();
    }

    @PostMapping("/idioma/ingresar/{idiomaP}")
    public ResponseEntity<String> ingresarIdioma(@PathVariable String idiomaP){
        try {
            idiomaImpl.ingresarIdioma(idiomaP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/idioma/eliminar/{idIdiomaP}")
    public ResponseEntity<String> eliminaridiomaPorId(@PathVariable int idIdiomaP){
        try {
            idiomaImpl.eliminaridiomaPorId(idIdiomaP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //=============================================
    @Autowired
    private GeneroServiceImpl generoImpl;

    @GetMapping("/genero/mostrar")
    public List<Genero> mostrarGeneros() {
        return (List<Genero>) generoImpl.mostrarGeneros();
    }
    
    @PostMapping("/genero/ingresar/{generoP}")
    public ResponseEntity<String> ingresarGenero(@PathVariable String generoP){
        try {
            generoImpl.ingresarGenero(generoP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/genero/eliminar/{idGeneroP}")
    public ResponseEntity<String> eliminarGenero(@PathVariable int idGeneroP){
        try {
            generoImpl.eliminarGeneroPorId(idGeneroP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //==================================
    @Autowired
    private ContratoService contratoimpl;

    @GetMapping("/contrato/mostrar")
    public List<Contrato> mostrarContratos() {
        return (List<Contrato>) contratoimpl.mostrarContratos();
    }

    @PostMapping("/contrato/ingresar/{contratoP}")
    public ResponseEntity<String> ingresarContrato(@PathVariable String contratoP){
        try {
            contratoimpl.ingresarContrato(contratoP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/contrato/eliminar/{idContratoP}")
    public ResponseEntity<String> eliminarContrato(@PathVariable int idContratoP){
        try {
            contratoimpl.eliminarContratoPorID(idContratoP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //======================================
    @Autowired
    private ModalidadService modalidadImpl;

    @GetMapping("/modalidad/mostrar")
    public List<Modalidad> mostrarModalidades() {
        return (List<Modalidad>) modalidadImpl.mostrarModalidades();
    }

    @PostMapping("/modalidad/ingresar/{modalidadP}")
    public ResponseEntity<String> ingresarModalidad(@PathVariable String modalidadP){
        try {
            modalidadImpl.ingresarModalidad(modalidadP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/modalidad/eliminar/{idModalidadP}")
    public ResponseEntity<String> eliminarModalidad(@PathVariable int idModalidadP){
        try {
            modalidadImpl.eliminarModalidadPorId(idModalidadP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //====================================================

    @Autowired
    private FormProfesionalService formProfesionalImpl;

    @GetMapping("/formacion-prof/mostrar")
    public List<FormacionProfesional> mostrarFormacionProf() {
        return (List<FormacionProfesional>) formProfesionalImpl.mostrarFormacionProfesionals();
    }

    @PostMapping("/formacion-prof/ingresar/{formacionProfP}")
    public ResponseEntity<String> ingresarFormacionProf(@PathVariable String formacionProfP){
        try {
            formProfesionalImpl.ingresarFormacionProf(formacionProfP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/formacion-prof/eliminar/{idFormacionProfP}")
    public ResponseEntity<String> eliminarFormacionProfPorId(@PathVariable int idFormacionProfP){
        try {
            formProfesionalImpl.eliminarFormacionProfPorId(idFormacionProfP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    //====================================================

    @Autowired
    private NivelIdiomaService nivelIdiomaService;

    @GetMapping("/nivel-idioma/mostrar")
    public List<NivelIdioma> mostrarNivelIdioma() {
        return (List<NivelIdioma>) nivelIdiomaService.mostrarNivelIdioma();
    }

    @PostMapping("/nivel-idioma/ingresar/{nivelIdioma}")
    public ResponseEntity<String> ingresarNivelIdioma(@PathVariable String nivelIdioma){
        try {
            nivelIdiomaService.ingresarNivelIdioma(nivelIdioma);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/nivel-idioma/eliminar/{idNivelIdioma}")
    public ResponseEntity<String> eliminarNivelIdioma(@PathVariable int idNivelIdioma){
        try {
            nivelIdiomaService.eliminarNivelIdioma(idNivelIdioma);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
