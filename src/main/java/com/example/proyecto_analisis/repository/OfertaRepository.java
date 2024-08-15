package com.example.proyecto_analisis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Solicitante;

public interface OfertaRepository extends JpaRepository<Solicitante, Integer> {

    @Query(value = "SELECT A.TITULO, " +
               "DATE_FORMAT(A.FECHA_PUBLICACION, '%M %d, %Y') AS fecha_publicacion, " +
               "DATE_FORMAT(A.FECHA_EXPIRACION, '%M %d, %Y') AS fecha_expiracion, " +
               "A.DESCRIPCION, " +
               "A.PLAZAS_DISPONIBLES, " +
               "B.URL_LOGO, " +
               "B.NOMBRE_EMPRESA, " +
               "CONCAT(C.NOMBRE_LUGAR, ', ', D.NOMBRE_LUGAR, ', ', E.NOMBRE_LUGAR) AS LUGAR, " +
               "F.TIPO_EMPLEO, " +
               "G.CONTRATO, " +
               "H.MODALIDAD, " +
               "I.NIVEL_ACADEMICO " +
               "FROM ofertas A " +
               "INNER JOIN empresa B ON (A.ID_EMPRESA = B.ID_EMPRESA) " +
               "INNER JOIN lugares C ON (A.ID_LUGAR = C.ID_LUGAR) " +
               "INNER JOIN lugares D ON (C.ID_LUGAR_PADRE = D.ID_LUGAR) " +
               "INNER JOIN lugares E ON (D.ID_LUGAR_PADRE = E.ID_LUGAR) " +
               "INNER JOIN tipo_empleo F ON (A.ID_TIPO_EMPLEO = F.ID_TIPO_EMPLEO) " +
               "INNER JOIN contratos G ON (A.ID_CONTRATO = G.ID_CONTRATO) " +
               "INNER JOIN modalidad H ON (A.ID_MODALIDAD = H.ID_MODALIDAD) " +
               "INNER JOIN nivel_academico I ON (A.ID_NIVEL_ACADEMICO = I.ID_NIVEL_ACADEMICO) " +
               "WHERE ID_OFERTA = :idOferta", nativeQuery = true)
    public Map<String,Object> obtenerDetalleOferta(@Param("idOferta") int idOferta);

    @Query(value = "SELECT B.PUESTO " +
        "FROM ofertas_puestos A " +
        "INNER JOIN puestos B " +
        "ON (A.ID_PUESTO = B.ID_PUESTO) " +
        "WHERE A.ID_OFERTA = :idOferta", 
    nativeQuery = true)
    public List<Object[]> obtenerPuestosOferta(@Param("idOferta") int idOferta);


    @Query(value = "SELECT B.FORMACION_PROFESIONAL " +
    "FROM requisitos_academicos A " +
    "INNER JOIN formacion_profesional B " +
    "ON (A.ID_FORMACION_PROFESIONAL = B.ID_FORMACION_PROFESIONAL) " +
    "WHERE A.ID_OFERTA = :idOferta", 
    nativeQuery = true)
    public List<Object[]> obtenerRequisitosAcademicosOferta(@Param("idOferta") int idOferta);

    @Query(value = "SELECT " +
    "B.PUESTO " +
    "FROM requisitos_laborales A " +
    "INNER JOIN puestos B " +
    "ON (A.ID_PUESTO = B.ID_PUESTO) " +
    "WHERE A.ID_OFERTA = :idOferta", 
    nativeQuery = true)
    public List<Object[]> obtenerRequisitosLaboralesOferta(@Param("idOferta") int idOferta);

    @Query(value = "SELECT " +
    "B.IDIOMA, " +
    "C.NIVEL_IDIOMA " +
    "FROM ofertas_idiomas A " +
    "INNER JOIN idiomas B " +
    "ON (A.ID_IDIOMA = B.ID_IDIOMA) " +
    "INNER JOIN nivel_idioma C " +
    "ON (A.ID_NIVEL_IDIOMA = C.ID_NIVEL_IDIOMA) " +
    "WHERE A.ID_OFERTA = :idOferta", 
    nativeQuery = true)
    public List<Object[]> obtenerIdiomasOferta(@Param("idOferta") int idOferta);

    @Query(value = "SELECT A.TITULO, " +
    "DATE_FORMAT(A.FECHA_PUBLICACION, '%M %d, %Y') AS fecha_publicacion, " +
    "DATE_FORMAT(A.FECHA_EXPIRACION, '%M %d, %Y') AS fecha_expiracion, " +
    "A.DESCRIPCION, " +
    "A.PLAZAS_DISPONIBLES, " +
    "B.URL_LOGO, " +
    "B.NOMBRE_EMPRESA, " +
    "CONCAT(C.NOMBRE_LUGAR, ', ', D.NOMBRE_LUGAR, ', ', E.NOMBRE_LUGAR) AS LUGAR, " +
    "F.TIPO_EMPLEO, " +
    "G.CONTRATO, " +
    "H.MODALIDAD, " +
    "I.NIVEL_ACADEMICO, " +
    "COUNT(*) AS CANTIDAD_APLICANTES " +
    "FROM ofertas A " +
    "INNER JOIN empresa B ON (A.ID_EMPRESA = B.ID_EMPRESA) " +
    "INNER JOIN lugares C ON (A.ID_LUGAR = C.ID_LUGAR) " +
    "INNER JOIN lugares D ON (C.ID_LUGAR_PADRE = D.ID_LUGAR) " +
    "INNER JOIN lugares E ON (D.ID_LUGAR_PADRE = E.ID_LUGAR) " +
    "INNER JOIN tipo_empleo F ON (A.ID_TIPO_EMPLEO = F.ID_TIPO_EMPLEO) " +
    "INNER JOIN contratos G ON (A.ID_CONTRATO = G.ID_CONTRATO) " +
    "INNER JOIN modalidad H ON (A.ID_MODALIDAD = H.ID_MODALIDAD) " +
    "INNER JOIN nivel_academico I ON (A.ID_NIVEL_ACADEMICO = I.ID_NIVEL_ACADEMICO) " +
    "INNER JOIN solicitudes J ON (A.ID_OFERTA = J.ID_OFERTA) " +
    "WHERE A.ID_OFERTA = :idOferta", 
    nativeQuery = true)
    public Map<String, Object> obtenerDetalleOfertaEmpresa(@Param("idOferta") int idOferta);


    @Query(value = "SELECT b.URL_FOTO_PERFIL "+
                    "from solicitudes a "+
                    "inner join solicitantes b on (a.ID_SOLICITANTE = b.ID_PERSONA) "+
                    "where a.id_oferta = :idOferta "+
                    "limit 4", nativeQuery=true)
    public List<Object[]> obtenerFotosSolicitantes(@Param("idOferta") int idOferta);
}
