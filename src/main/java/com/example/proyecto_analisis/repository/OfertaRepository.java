package com.example.proyecto_analisis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Solicitante;

import jakarta.transaction.Transactional;

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


    @Query(value = "select count(1) as aplicando "+
                    "from solicitudes "+
                    "where ID_SOLICITANTE=:idSolicitante and ID_OFERTA=:idOferta", nativeQuery = true)
    public int obtenerAplicacionSolicitante(@Param("idOferta") int idOferta, @Param("idSolicitante") int idSolicitante);

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


    //============================
    @Query(value = "SELECT "+
                    "    CAST(e.NOMBRE_EMPRESA AS CHAR) AS NOMBRE_EMPRESA, "+
                    "    CAST(COUNT(o.ID_OFERTA) AS CHAR) AS ofertasActivas, "+
                    "    CAST(AVG(sub.solicitantes_por_oferta) AS CHAR) AS promedio_solicitantes "+
                    "FROM "+
                    "    empresa e "+
                    "LEFT JOIN "+
                    "    ofertas o ON e.ID_EMPRESA = o.ID_EMPRESA AND o.ESTADO_OFERTA = 1 "+
                    "LEFT JOIN "+
                    "    (SELECT o.ID_OFERTA, COUNT(s.ID_SOLICITANTE) AS solicitantes_por_oferta "+
                    "    FROM ofertas o "+
                    "    LEFT JOIN solicitudes s ON o.ID_OFERTA = s.ID_OFERTA "+
                    "    WHERE o.ESTADO_OFERTA = 1 "+
                    "    AND o.ID_EMPRESA = :idEmpresaP "+
                    "    GROUP BY o.ID_OFERTA "+
                    "    ) AS sub ON o.ID_OFERTA = sub.ID_OFERTA "+
                    "WHERE "+
                    "    e.ID_EMPRESA = :idEmpresaP "+
                    "GROUP BY "+
                    "    e.NOMBRE_EMPRESA;", nativeQuery = true)
    public List<Object[]> obtenerEstadisticasEmpresa(@Param("idEmpresaP") int idEmpresaP);

    //Promedio hombres
    @Query(value = "SELECT "+
                    "    (COUNT(CASE WHEN p.GENERO_ID_GENERO = 1 THEN 1 END) * 100.0 / COUNT(*)) AS porcentajeHombres "+
                    "FROM ofertas o "+
                    "INNER JOIN solicitudes s ON o.ID_OFERTA = s.ID_OFERTA "+
                    "INNER JOIN solicitantes soli ON s.ID_SOLICITANTE = soli.ID_PERSONA "+
                    "INNER JOIN personas p ON soli.ID_PERSONA = p.ID_PERSONA "+
                    "WHERE o.ID_EMPRESA = :idEmpresaP;",nativeQuery = true)
    public Integer obtenerPromedioHombres(@Param("idEmpresaP") int idEmpresaP);

    //ultimas ofertas
    @Query(value = "SELECT "+
                    "    ID_OFERTA, "+
                    "    TITULO, "+
                    "    SUBSTRING(DESCRIPCION, 1, 200) AS DESCRIPCION, "+
                    "    DATE_FORMAT(FECHA_PUBLICACION, '%d %b, %Y') as FECHA_PUBLICACION "+
                    "FROM ofertas "+
                    "WHERE ID_EMPRESA = :idEmpresaP AND ESTADO_OFERTA = 1 "+
                    "ORDER BY FECHA_PUBLICACION DESC "+
                    "LIMIT 3;", nativeQuery = true)
    public List<Object[]> obtenerUltimasOfertasEmpresa(@Param("idEmpresaP") int idEmpresaP);

    //notificaciones
    @Query(value = "SELECT"+
                    "    ne.ID_NOTIFICACION_EMP,"+
                    "    ne.TITULO,"+
                    "    DATE_FORMAT(ne.fecha, '%d %b, %Y') as fechaNotificion,"+
                    "    ne.estado_visualizacion,"+
                    "    em.url_logo "+
                    "FROM notificaciones_empresas ne "+
                    "INNER JOIN empresa em on ne.ID_EMPRESA = em.ID_EMPRESA "+
                    "WHERE em.ID_EMPRESA = :idEmpresaP ", nativeQuery = true)
    public List<Object[]> obtenerNotificionesEmpresa(@Param("idEmpresaP") int idEmpresaP);

    // Agg oferta-puesto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ofertas_puestos(ID_PUESTO, ID_OFERTA) "+
                    "VALUES (:idPuestoP,:idOfertaP)", nativeQuery = true)
    public void ingresarPuestoOferta(@Param("idPuestoP") int idPuestoP, @Param("idOfertaP") int idOfertaP);

    // Agg oferta-idiomas
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ofertas_idiomas(ID_NIVEL_IDIOMA, ID_OFERTA, ID_IDIOMA) "+
                    "VALUES (:idNivelIdiomaP,:idOfertaP,:idIdiomaP)", nativeQuery = true)
    public void ingresarIdiomaOferta(@Param("idNivelIdiomaP") int idNivelIdiomaP,
                                    @Param("idOfertaP") int idOfertaP,
                                    @Param("idIdiomaP") int idIdiomaP
    );
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

    @Query(value = "SELECT ID_OFERTA,"+
                    "        TITULO,"+
                    "        SUBSTRING(DESCRIPCION,1,200),"+
                    "        DATE_FORMAT(FECHA_PUBLICACION, '%d %b, %Y') as FECHA_PUBLICACION,"+
                    "        ESTADO_OFERTA "+
                    "FROM ofertas "+
                    "WHERE ID_EMPRESA = :idEmpresaP "+
                    "ORDER BY FECHA_PUBLICACION DESC;",nativeQuery = true)
    public List<Object[]> obtenerOfertasPorEmpresaId(@Param("idEmpresaP") int idEmpresaP);
}
