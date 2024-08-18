package com.example.proyecto_analisis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Oferta;
import com.example.proyecto_analisis.models.dto.NvaOfertaDTO;
import com.example.proyecto_analisis.models.dto.OfertaDTO;
import com.example.proyecto_analisis.models.dto.OfertaEmpresaHomeDTO;
import com.example.proyecto_analisis.repository.OfertaModelRepository;
import com.example.proyecto_analisis.repository.OfertaRepository;

@Service
public class OfertaService {

    
    
    @Autowired
    private OfertaModelRepository ofertaModelRepository;
    
    @Autowired
    private OfertaRepository ofertaRepository;

    public OfertaDTO obtenerDetalleOferta(int idOferta, int idSolicitante){
        try {
            OfertaDTO oferta = new OfertaDTO();

            Map<String, Object> detalleOferta = ofertaRepository.obtenerDetalleOferta(idOferta);
            int aplicando = ofertaRepository.obtenerAplicacionSolicitante(idOferta, idSolicitante);

            oferta.setNombreOferta(detalleOferta.get("titulo").toString());
            oferta.setNombreEmpresa(detalleOferta.get("nombre_empresa").toString());
            oferta.setFechaPublicacion(detalleOferta.get("fecha_publicacion").toString());
            oferta.setFechaExpiracion(detalleOferta.get("fecha_expiracion").toString());
            oferta.setDescripcion(detalleOferta.get("descripcion").toString());
            oferta.setVacantes((int) detalleOferta.get("plazas_disponibles"));
            oferta.setUrlEmpresa(detalleOferta.get("url_logo").toString());
            oferta.setLugar(detalleOferta.get("lugar").toString());
            oferta.setTipoEmpleo(detalleOferta.get("tipo_empleo").toString());
            oferta.setTipoContratacion(detalleOferta.get("contrato").toString());
            oferta.setModalidad(detalleOferta.get("modalidad").toString());
            oferta.setNivelAcademico(detalleOferta.get("nivel_academico").toString());
            oferta.setAplicando(aplicando);

            List<Object[]> puestosOferta = ofertaRepository.obtenerPuestosOferta(idOferta); //consulta con los puestos
            List<String> puestosStr = new ArrayList<>(); //Lista de Strings con los puestos
            
            for (Object[] puesto : puestosOferta) {
                puestosStr.add(puesto[0].toString());
            }

            oferta.setCargos(puestosStr);

            List<Object[]> requisitosAcademicos = ofertaRepository.obtenerRequisitosAcademicosOferta(idOferta); //consulta con los puestos
            List<String> requisitosAcademicosStr = new ArrayList<>(); //Lista de Strings con los puestos
            
            for (Object[] requisito : requisitosAcademicos) {
                requisitosAcademicosStr.add(requisito[0].toString());
            }

            oferta.setRequisitosAcademicos(requisitosAcademicosStr);
            
            List<Object[]> requisitosLaborales = ofertaRepository.obtenerRequisitosLaboralesOferta(idOferta); //consulta con los puestos
            List<String> requisitosLaboralesStr = new ArrayList<>(); //Lista de Strings con los puestos
            
            for (Object[] requisito : requisitosLaborales) {
                requisitosLaboralesStr.add(requisito[0].toString());
            }

            oferta.setExperienciaRequerida(requisitosLaboralesStr);

            List<Object[]> objIdiomas = ofertaRepository.obtenerIdiomasOferta(idOferta); //consulta con los puestos
            List<Map<String,Object>> idiomas = objIdiomas.stream()
            .map(obj-> {
                    Map<String, Object> idioma = new LinkedHashMap<>();
                        idioma.put("nombre", obj[0]);
                        idioma.put("nivel", obj[1]);
                        return idioma;
                }).collect(Collectors.toList());

            oferta.setIdiomas(idiomas);

            return oferta;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OfertaEmpresaHomeDTO obtenerHomeEmpresa(int idEmpresaP){
        
        OfertaEmpresaHomeDTO homeEmpresaDTO = new OfertaEmpresaHomeDTO();

        List<Object[]> estadisticasHomeEmpresa = ofertaRepository.obtenerEstadisticasEmpresa(idEmpresaP);

        List<Map<String,Object>> estadisticas = estadisticasHomeEmpresa.stream()
            .map(obj-> {
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("nombreEmpresa", obj[0]);
                map.put("cantidadOfertasActivas", obj[1]);
                map.put("promedioSolicitantesOfertas", obj[2]);
                map.put("fotoEmpresa", obj[3]);
                return map;
            }).collect(Collectors.toList());

        Map<String, Object> est = estadisticas.get(0);
        

        //Porcentajes
        Integer promHombre = ofertaRepository.obtenerPromedioHombres(idEmpresaP);
        Integer promMujeres = 100 - promHombre;

        String promHombreFormateado = String.valueOf(promHombre + "%");
        String promMujerFormateado = String.valueOf(promMujeres + "%");

        //Ofertas
        List<Object[]> ofertasActivas = ofertaRepository.obtenerUltimasOfertasEmpresa(idEmpresaP);

        //Notificaciones
        List<Object[]> notificaciones = ofertaRepository.obtenerNotificionesEmpresa(idEmpresaP);
        //Construccion DTO
        homeEmpresaDTO.setNombreEmpresa((String) est.get("nombreEmpresa"));
        homeEmpresaDTO.setLogoEmpresa((String) est.get("fotoEmpresa"));
        homeEmpresaDTO.setCantOfertasAct((String) est.get("cantidadOfertasActivas"));
        homeEmpresaDTO.setPromSolicitanteOfer((String) est.get("promedioSolicitantesOfertas"));
        homeEmpresaDTO.setPorcentajeHombresAplicante(promHombreFormateado);
        homeEmpresaDTO.setPorcentajeMujeresAplicante(promMujerFormateado);
        homeEmpresaDTO.setOfertasActivas(ofertasActivas);
        homeEmpresaDTO.setNotificaciones(notificaciones);

        return homeEmpresaDTO;

    }

    // Ingresar nueva oferta
    public int ingresarNvaOferta(Oferta oferta){
        Oferta nvaOferta = ofertaModelRepository.save(oferta);
        return nvaOferta.getIdOferta();
    }

    // Ingresar puesto-oferta
    public void ingresarPuestoOferta(int idPuestoP, int idOfertaP){
        ofertaRepository.ingresarPuestoOferta(idPuestoP, idOfertaP);
    }

    // Ingresar idioma-oferta
    public void ingresarIdiomaOferta(int idNivelIdiomaP, int idOfertaP, int idIdiomaP){
        ofertaRepository.ingresarIdiomaOferta(idNivelIdiomaP, idOfertaP, idIdiomaP);
    }

    // Ingresar requisitos-academicos
    public void ingresarReqAcadeOferta(int idOfertaP, int idFormacionProf){
        ofertaRepository.ingresarReqAcadeOferta(idOfertaP, idFormacionProf);
    }

    // Ingresar requisitos laborales
    public void ingresarReqLaboOferta(int idPuestoP, int idOfertaP){
        ofertaRepository.ingresarReqLaboOferta(idPuestoP, idOfertaP);
    }

    public OfertaDTO obtenerDetalleOfertaEmpresa(int idOferta){
        try {
            OfertaDTO oferta = new OfertaDTO();

            Map<String, Object> detalleOferta = ofertaRepository.obtenerDetalleOfertaEmpresa(idOferta);
            int cantidadAplicantes = ofertaRepository.obtenerCantidadAplicantesOferta(idOferta);

            oferta.setNombreOferta(detalleOferta.get("titulo").toString());
            oferta.setNombreEmpresa(detalleOferta.get("nombre_empresa").toString());
            oferta.setFechaPublicacion(detalleOferta.get("fecha_publicacion").toString());
            oferta.setFechaExpiracion(detalleOferta.get("fecha_expiracion").toString());
            oferta.setDescripcion(detalleOferta.get("descripcion").toString());
            oferta.setVacantes((int) detalleOferta.get("plazas_disponibles"));
            oferta.setUrlEmpresa(detalleOferta.get("url_logo").toString());
            oferta.setLugar(detalleOferta.get("lugar").toString());
            oferta.setTipoEmpleo(detalleOferta.get("tipo_empleo").toString());
            oferta.setTipoContratacion(detalleOferta.get("contrato").toString());
            oferta.setModalidad(detalleOferta.get("modalidad").toString());
            oferta.setNivelAcademico(detalleOferta.get("nivel_academico").toString());
            oferta.setCantidadAplicantes(cantidadAplicantes);

            List<Object[]> puestosOferta = ofertaRepository.obtenerPuestosOferta(idOferta); //consulta con los puestos
            List<String> puestosStr = new ArrayList<>(); //Lista de Strings con los puestos
            
            for (Object[] puesto : puestosOferta) {
                puestosStr.add(puesto[0].toString());
            }

            oferta.setCargos(puestosStr);

            List<Object[]> requisitosAcademicos = ofertaRepository.obtenerRequisitosAcademicosOferta(idOferta); //consulta con los puestos
            List<String> requisitosAcademicosStr = new ArrayList<>(); //Lista de Strings con los puestos
            
            for (Object[] requisito : requisitosAcademicos) {
                requisitosAcademicosStr.add(requisito[0].toString());
            }

            oferta.setRequisitosAcademicos(requisitosAcademicosStr);
            
            List<Object[]> requisitosLaborales = ofertaRepository.obtenerRequisitosLaboralesOferta(idOferta); //consulta con los puestos
            List<String> requisitosLaboralesStr = new ArrayList<>(); //Lista de Strings con los puestos
            
            for (Object[] requisito : requisitosLaborales) {
                requisitosLaboralesStr.add(requisito[0].toString());
            }

            oferta.setExperienciaRequerida(requisitosLaboralesStr);

            List<Object[]> fotosAplicantes = ofertaRepository.obtenerFotosSolicitantes(idOferta); //consulta con los puestos
            List<String> fotosAplicantesStr = new ArrayList<>(); //Lista de Strings con las imagenes de solicitantes
            
            for (Object[] foto : fotosAplicantes) {
                if (foto!=null) {
                fotosAplicantesStr.add(foto[0].toString());
                }
            }

            oferta.setAplicantesImg(fotosAplicantesStr);

            List<Object[]> objIdiomas = ofertaRepository.obtenerIdiomasOferta(idOferta); //consulta con los puestos
            List<Map<String,Object>> idiomas = objIdiomas.stream()
            .map(obj-> {
                    Map<String, Object> idioma = new LinkedHashMap<>();
                        idioma.put("nombre", obj[0]);
                        idioma.put("nivel", obj[1]);
                        return idioma;
                }).collect(Collectors.toList());

            oferta.setIdiomas(idiomas);

            return oferta;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Obtener ofertas por el id de la empresa
    public List<Map<String,Object>> obtenerOfertasPorEmpresaId(int idEmpresaP){

        List<Object[]> objOfertas = ofertaRepository.obtenerOfertasPorEmpresaId(idEmpresaP);

        List<Map<String,Object>> ofertas = objOfertas.stream()
            .map(obj ->{
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("idOfertas", obj[0]);
                map.put("nombreOferta", obj[1]);
                map.put("descripcion", obj[2]);
                map.put("fechaPublicacion", obj[3]);
                map.put("estadoOferta", obj[4]);
                return map;
            }).collect(Collectors.toList());

            return ofertas;
    }

    // Eliminar oferta por ifOferta
    public void eliminarOfertaPorId(int idOferta){
        ofertaRepository.eliminarOfertaPorId(idOferta);
    }

    //=======================================
    //TRAER OFERTA EDITABLE
    public NvaOfertaDTO obtenerOfertaEditable(int idOferta){
        
        NvaOfertaDTO nvaOfertaDTO = new NvaOfertaDTO();

        Object[] objOferta = ofertaRepository.obtenerOfertaEditable(idOferta);
        if (objOferta instanceof Object[]) {
            Object[] data = (Object[]) objOferta[0];

            nvaOfertaDTO.setTitulo((String )data[0]);
            nvaOfertaDTO.setPlazasDisponibles((Integer) data[1]);
            nvaOfertaDTO.setFechaExpiracion((Date) data[2]);
            nvaOfertaDTO.setDescripcion((String) data[3]);
            nvaOfertaDTO.setTipoEmpleo((Integer) data[4]);
            nvaOfertaDTO.setTipoContrato((Integer) data[5]);
            nvaOfertaDTO.setNivelAcademico((Integer) data[6]);
            nvaOfertaDTO.setModalidad((Integer) data[7]);
            nvaOfertaDTO.setLugar((Integer) data[8]);

            List<Integer> puestos = ofertaRepository.obtenerOfertaPuestoEditable(idOferta);
            List<Integer> reqAcade = ofertaRepository.obtenerOfertaReqAcadeEditable(idOferta);
            List<Integer> reqLabo = ofertaRepository.obtenerOfertaReqLaborEditable(idOferta);

            List<Object[]> objIdiomas = ofertaRepository.obtenerOfertaIdiomaEditable(idOferta);

            List<Map<String,Integer>> idiomas = objIdiomas.stream()
                .map(obj -> {
                    Map<String,Integer> map = new LinkedHashMap<>();
                    map.put("idIdioma", (Integer) obj[0]);
                    map.put("idNivelIdioma", (Integer) obj[1]);
                    return map;
                }).collect(Collectors.toList());

            nvaOfertaDTO.setExperienciaLaboral(reqLabo);
            nvaOfertaDTO.setRequisitosAcademicos(reqAcade);
            nvaOfertaDTO.setPuestos(puestos);
            nvaOfertaDTO.setIdiomas(idiomas);

            return nvaOfertaDTO;
        } else {
            return null;
        }

    }

    //=====================================
    //EDITAR OFERTA
    public void editarOferta(
        int idOfertaP,
        NvaOfertaDTO nvaOfertaDTO
    ){
        ofertaRepository.vaciarTablasIntermediasOfertas(idOfertaP);

        ofertaRepository.actualizarTablaOferta(
            nvaOfertaDTO.getTitulo(),
            nvaOfertaDTO.getPlazasDisponibles(),
            nvaOfertaDTO.getFechaExpiracion(),
            nvaOfertaDTO.getDescripcion(),
            nvaOfertaDTO.getTipoEmpleo(),
            nvaOfertaDTO.getTipoContrato(),
            nvaOfertaDTO.getNivelAcademico(),
            nvaOfertaDTO.getModalidad(),
            nvaOfertaDTO.getLugar(),
            idOfertaP
        );

        List<Integer> puestos = nvaOfertaDTO.getPuestos();
        for (Integer idPuesto : puestos) {
            ofertaRepository.ingresarPuestoOferta(idPuesto, idOfertaP);
        }

        List<Integer> reqAca = nvaOfertaDTO.getRequisitosAcademicos();
        for (Integer idReqAca : reqAca) {
            ofertaRepository.ingresarReqAcadeOferta(idOfertaP,idReqAca);
        }

        List<Integer> reqLab = nvaOfertaDTO.getExperienciaLaboral();
        for (Integer idReqLab : reqLab) {
            ofertaRepository.ingresarReqLaboOferta(idReqLab,idOfertaP);
        }

        List<Map<String,Integer>> idiomasInfo = nvaOfertaDTO.getIdiomas();

        for (Map<String,Integer> map : idiomasInfo) {
            Integer idIdioma = map.get("idIdioma");
            Integer idNivelIdioma = map.get("idNivelIdioma");

            ofertaRepository.ingresarIdiomaOferta(idNivelIdioma, idOfertaP, idIdioma);
        }

    }

    // Obtener aplicantes 
    public Map<String,Object> obtenerDetalleAplicanteOferta(int idOfertaP){
        Object[] objOferta = ofertaRepository.obtenerDetalleOfertaAplicante(idOfertaP);
        Object[] data = (Object[]) objOferta[0];

        List<Object[]> objSolicitantes = ofertaRepository.obtenerDetalleAplicanteOferta(idOfertaP);

        List<Map<String,Object>> solicitante = objSolicitantes.stream()
            .map(obj -> {
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("idSolicitante", obj[0]);
                map.put("nombreCompleto", obj[1]);
                map.put("fechaSolicitud", obj[2]);
                map.put("idEstadoSolicitud", obj[3]);
                return map;
            }).collect(Collectors.toList());


        //Objeto completo
        Map<String,Object> solicitanteOferta = new LinkedHashMap<>();
        solicitanteOferta.put("idOferta", data[0]);
        solicitanteOferta.put("titulo", data[1]);
        solicitanteOferta.put("fechaPublicacion", data[2]);
        solicitanteOferta.put("aplicantes", solicitante);

        return solicitanteOferta;
    }
}
