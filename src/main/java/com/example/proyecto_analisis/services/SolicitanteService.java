package com.example.proyecto_analisis.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.ExperienciaLaboral;
import com.example.proyecto_analisis.models.Solicitante;
import com.example.proyecto_analisis.models.dto.UsuarioDTO;
import com.example.proyecto_analisis.models.dto.VistaInicioSolicitante_DTO;
import com.example.proyecto_analisis.repository.ExperienciaLaboralRepository;
import com.example.proyecto_analisis.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
    
    @Autowired
    private SolicitanteRepository solicitanteRepositorio;

    @Autowired
    private ExperienciaLaboralRepository expLabRepositorio;

    public UsuarioDTO obtenerSolicitantePorId(int idUsuario) {
        try {
            Map<String, Object> solicitanteData = solicitanteRepositorio.obtenerSolicitantePorId(idUsuario);
            Map<String, Object> lugarCompleto = solicitanteRepositorio.obtenerLugarCompletoResidencia(idUsuario);
            UsuarioDTO solicitante = new UsuarioDTO();
            
            solicitante.setPrimerNombre(safeToString(solicitanteData.get("primer_nombre")));
            solicitante.setSegundoNombre(safeToString(solicitanteData.get("segundo_nombre")));
            solicitante.setPrimerApellido(safeToString(solicitanteData.get("primer_apellido")));
            solicitante.setSegundoApellido(safeToString(solicitanteData.get("segundo_apellido")));
            solicitante.setIdentificacion(safeToString(solicitanteData.get("identificacion")));
            solicitante.setTelefono(safeToString(solicitanteData.get("telefono")));
            solicitante.setIdGenero(safeToString(solicitanteData.get("genero_id_genero")));
            solicitante.setCorreo(safeToString(solicitanteData.get("correo")));
            solicitante.setContrasena(safeToString(solicitanteData.get("contrasena")));
    
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = solicitanteData.get("fecha_nacimiento") != null ? formatter.parse(solicitanteData.get("fecha_nacimiento").toString()) : null;
            solicitante.setFechaNacimiento(birthDate);
            
            solicitante.setTitular(safeToString(solicitanteData.get("titular")));
            solicitante.setDescripcion(safeToString(solicitanteData.get("descripcion")));
            solicitante.setIdEstadoCivil(safeToString(solicitanteData.get("id_estado_civil")));
            solicitante.setIdLugarNacimiento(safeToString(solicitanteData.get("id_lugar_nacimiento")));
            solicitante.setIdPaisResidencia(safeToString(lugarCompleto.get("pais")));
            solicitante.setIdDepartamentoResidencia(safeToString(lugarCompleto.get("departamento")));
            solicitante.setIdMunicipioResidencia(safeToString(lugarCompleto.get("municipio")));
    
            return solicitante;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String safeToString(Object value) {
        return value != null ? value.toString() : null;
    }
    

    public void ingresarSolicitante(Solicitante solicitante){
        solicitanteRepositorio.save(solicitante);
        
    }

    public int autenticarSolicitante(String correo, String contrasena){

        Solicitante solicitante = solicitanteRepositorio.autenticarSolicitante(correo, contrasena);

        if(solicitante == null){
            return 0;
        } else {
            return solicitante.getIdPersona();
        }

    }

    public Solicitante actualizarSolicitante(int idPersona, Solicitante actSolicitante){

        Optional<Solicitante> optSolicitante = solicitanteRepositorio.findById(idPersona);

        if(optSolicitante.isPresent()){

            Solicitante extSolicitante = optSolicitante.get();

            extSolicitante.setCorreo(actSolicitante.getCorreo());
            extSolicitante.setContrasena(actSolicitante.getContrasena());
            extSolicitante.setFechaNacimiento(actSolicitante.getFechaNacimiento());
            extSolicitante.setTitular(actSolicitante.getTitular());
            extSolicitante.setDescripcion(actSolicitante.getDescripcion());
            extSolicitante.setEstadoCivil(actSolicitante.getEstadoCivil());
            extSolicitante.setLugarNacimiento(actSolicitante.getLugarNacimiento());
            extSolicitante.setLugarResidencia(actSolicitante.getLugarResidencia());

            return solicitanteRepositorio.save(extSolicitante);
        }else {
            return null;
        }
    }

    public void aggExperienciaLaboral(ExperienciaLaboral expLaboral){
        expLabRepositorio.save(expLaboral); 
    }

    // Agg experiencia laboral
    public void insertarHistorialAcademico(
        int idPersonaP, 
        int idNivelAcademicoP, 
        int idFormacionAcP, 
        String tituloP, 
        Date fechaEgresoP, 
        String INSTITUCIONP
    ){
        solicitanteRepositorio.insertarHistorialAcademico(idPersonaP, idNivelAcademicoP, idFormacionAcP, tituloP, fechaEgresoP, INSTITUCIONP);
    }

    //Agg idiomas del solicitante
    public void ingresarSolicitanteIdioma(
        int idPersonaP,
        int idIdiomaP,
        int idNivelP
    ){
        solicitanteRepositorio.ingresarSolicitanteIdioma(idPersonaP, idIdiomaP, idNivelP);
    }

    //Agg informacion de seguro del solicitante
    public void insertarSeguroSolicitante(
        int idPersonaP,
        int idTipoSeguroP,
        Date fechaAfiliacionP,
        Date fechaExpiracionP,
        String numeroAfiliacionP
    ){
        solicitanteRepositorio.insertarSeguroSolicitante(idPersonaP,idTipoSeguroP,fechaAfiliacionP,fechaExpiracionP,numeroAfiliacionP);
    }

    // Agg familiar de solicitante
    public void ingresarFamiliarSolicitante(
        int idFamiliar, 
        int idParentesco, 
        int idSolicitante
    ){
        solicitanteRepositorio.ingresarFamiliarSolicitante(idFamiliar, idParentesco, idSolicitante);
    }

    // Agg historial medico solicitante
    public void ingresarHistorialMedico(
        String descripcion, 
        int idCondicion, 
        int idPersona
    ){
        solicitanteRepositorio.ingresarHistorialMedico(descripcion, idCondicion, idPersona);
    }

    // Obtener solicitudes del solicitante
    public List<Map<String,Object>> obtenerAplicacionesSolicitante(int idSolicitanteP){

        List<Object[]> objSolicitudes = solicitanteRepositorio.obtenerAplicacionesSolicitante(idSolicitanteP);

        if (objSolicitudes.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<Map<String,Object>> solicitudes = objSolicitudes.stream()
                .map(obj -> {
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("idOferta", obj[0]);
                    map.put("fechaPublicacionOferta", obj[1]);
                    map.put("tituloOferta", obj[2]);
                    map.put("nombreEmpresa", obj[3]);
                    map.put("fechaSolicitud", obj[4]);
                    map.put("estadoSolicitud", obj[5]);
                    map.put("url_logo", obj[6]);

                    return map;
                }).collect(Collectors.toList());

            return solicitudes;
        }
    }

    // Obtener detalle notificacion
    public Map<String,Object> obtenerDetalleNotificacionSolic(int idNotifSolicitanteP){

        List<Object[]> objNotificacion = solicitanteRepositorio.obtenerDetalleNotificacionSolic(idNotifSolicitanteP);

        if(objNotificacion.isEmpty()){
            return Collections.emptyMap();
        }else{
            List<Map<String,Object>> notificaciones = objNotificacion.stream()
                .map(obj -> {
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("idNotificacion", obj[0]);
                    map.put("titulo", obj[1]);
                    map.put("logoEmpresa", obj[2]);
                    map.put("nombreEmpresa", obj[3]);
                    map.put("descripcion", obj[4]);
                    map.put("fecha", obj[5]);
                    map.put("idSolicitud", obj[6]);

                    return map;
                }).collect(Collectors.toList());

            return notificaciones.get(0);
        }
        
    }

    // Cambiar estado de notificacion solicitante
    public void cambiarEstadoNotiSolic(int idNotifSolicitanteP){
        solicitanteRepositorio.cambiarEstadoNotiSolic(idNotifSolicitanteP);
    }
    public int ObtenerDatosVistaHome(String correo, String contrasena){

        Solicitante solicitante = solicitanteRepositorio.autenticarSolicitante(correo, contrasena);

        if(solicitante == null){
            return 0;
        } else {
            return solicitante.getIdPersona();
        }

    }

        // Obtener datos para vista de usuario
        public VistaInicioSolicitante_DTO obtenerDatosParaVistaSolicitante(int idSolicitanteP){

            VistaInicioSolicitante_DTO objVista = new VistaInicioSolicitante_DTO();

            List<Object[]> objOfertas = solicitanteRepositorio.obtenerOfertasFeedUsuario(idSolicitanteP);
    
            List<Map<String,Object>> solicitudes = objOfertas.stream()
            .map(obj -> {
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("idOferta", obj[0]);
                map.put("fechaPublicacionOferta", obj[1]);
                map.put("tituloOferta", obj[2]);
                map.put("nombreEmpresa", obj[3]);
                map.put("descripcion", obj[4]);
                map.put("url_logo", obj[5]);

                return map;
            }).collect(Collectors.toList());
            List<Object[]> objNotificacion = solicitanteRepositorio.obtenerVistaPrevNotificacion(idSolicitanteP);
            List<Map<String,Object>> notificaciones = objNotificacion.stream()
                .map(obj -> {
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("idNotificacion", obj[0]);
                    map.put("titulo", obj[1]);
                    map.put("fecha", obj[2]);
                    map.put("estado", obj[3]);
                    map.put("descripcion", obj[4]);

                    return map;
                }).collect(Collectors.toList());
            List<Object[]> objCategorias = solicitanteRepositorio.obtenerCategorias();
            List<Map<String,Object>> categorias = objCategorias.stream()
                .map(obj -> {
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("idCategoria", obj[0]);
                    map.put("categoria", obj[1]);
                    return map;
                }).collect(Collectors.toList());
            objVista.setCategorias(categorias);
            objVista.setIdSolicitante(idSolicitanteP);
            objVista.setNotificaciones(notificaciones);
            objVista.setOfertas(solicitudes);

            return objVista;

        }
        //Aplicar a una oferta
        public void aplicarAOferta(int idSolicitante, int idOferta){
            solicitanteRepositorio.insertarSolicitud(idSolicitante, idOferta);;
        }

}
