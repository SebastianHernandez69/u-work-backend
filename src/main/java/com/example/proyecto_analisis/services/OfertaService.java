package com.example.proyecto_analisis.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.dto.OfertaDTO;
import com.example.proyecto_analisis.repository.OfertaRepository;

@Service
public class OfertaService {
    
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

    public OfertaDTO obtenerDetalleOfertaEmpresa(int idOferta){
        try {
            OfertaDTO oferta = new OfertaDTO();

            Map<String, Object> detalleOferta = ofertaRepository.obtenerDetalleOfertaEmpresa(idOferta);

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
            Long cantidadAplicantes = (Long) detalleOferta.get("cantidad_aplicantes");
            oferta.setCantidadAplicantes(cantidadAplicantes.intValue());

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

}
