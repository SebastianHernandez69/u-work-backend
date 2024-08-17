package com.example.proyecto_analisis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Persona;
import com.example.proyecto_analisis.models.interfaces.PersonaInterface;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    
    @Query(value = "SELECT id_persona, CONCAT(p.PRIMER_NOMBRE, ' ', IFNULL(p.SEGUNDO_NOMBRE, ''), ' ', p.PRIMER_APELLIDO, ' ', p.SEGUNDO_APELLIDO) AS nombreCompleto " +
                   "FROM PERSONAS p " +
                   "WHERE p.IDENTIFICACION = :identificacion", nativeQuery = true)
    public PersonaInterface obtenerUsuarioPorIdentidad(@Param("identificacion")String identificacion);
    
    @Query(value = "SELECT CONCAT(p.PRIMER_NOMBRE, ' ', IFNULL(p.SEGUNDO_NOMBRE, ''), ' ', p.PRIMER_APELLIDO, ' ', p.SEGUNDO_APELLIDO) AS nombreCompleto" +
                    " FROM personas p INNER JOIN familiares f "+
                    "on p.ID_PERSONA = f.ID_FAMILIAR WHERE f.ID_SOLICITANTE = :idSolicitante;",nativeQuery = true)
    public List<String> obtenerFamiliares(@Param("idSolicitante") int idSolicitante);

    //PERFIL APLICANTE
    @Query(value = "SELECT concat(p.primer_nombre, ' ', p.primer_apellido) as nombre,"+
                    "    so.titular as titular,"+
                    "    l.nombre_lugar as lugarResidencia,"+
                    "    CONCAT(p.telefono,'') as telefono,"+
                    "    so.correo as correo,"+
                    "    DATE_FORMAT(so.FECHA_NACIMIENTO, '%d %b, %Y') AS fechaNacimiento,"+
                    "    so.descripcion as descripcion "+
                    "FROM personas p "+
                    "INNER JOIN solicitantes so on p.ID_PERSONA = so.ID_PERSONA "+
                    "INNER JOIN lugares l on so.ID_LUGAR_RESIDENCIA = l.ID_LUGAR "+
                    "WHERE p.ID_PERSONA = :idPersona;",
           nativeQuery = true)
    public List<Object[]> obtenerDatosPerfilApp(@Param("idPersona") int idPersona);
    
    // FORMACION
    @Query(value = "select "+
                    "    ha.titulo, "+
                    "    ha.institucion, "+
                    "    DATE_FORMAT(ha.FECHA_EGRESO, '%d %b, %Y') AS fechaExpedicion,"+
                    "    na.nivel_academico "+
                    "from historial_academico ha "+
                    "INNER JOIN NIVEL_ACADEMICO na on ha.ID_NIVEL_ACADEMICO = na.ID_NIVEL_ACADEMICO "+
                    "WHERE ha.ID_PERSONA = :idPersona;",
            nativeQuery = true)
    public List<Object[]> obtenerFormacionPA(@Param("idPersona") int idPersona);

    // HISTORIAL MEDICO
    @Query(value =  "select "+
                    "    cm.condicion_medicas as titulo,"+
                    "    hm.descripcion "+
                    "from historial_medico hm "+
                    "INNER JOIN condiciones_medicas cm on hm.ID_CONDICION_MEDICA = cm.ID_CONDICIONE_MEDICA "+
                    "where hm.ID_PERSONA = :idPersona;",
            nativeQuery = true)
    public List<Object[]> obtenerHistorialMedicoPA(@Param("idPersona") int idPersona);

    // SEGUROS APLICANTE
    @Query(value = "SELECT "+
                    "    ts.tipo_seguro,"+
                    "    DATE_FORMAT(ss.fecha_afiliacion, '%d %b, %Y') AS fechaAfiliacion,"+
                    "    DATE_FORMAT(ss.fecha_expiracion, '%d %b, %Y') AS fechaExpiracion,"+
                    "    ss.numero_afiliacion "+
                    "FROM seguros_solicitantes ss "+
                    "INNER JOIN tipo_seguros ts on ss.ID_TIPO_SEGURO = ts.ID_TIPO_SEGURO "+
                    "where ss.ID_PERSONA = :idPersona;",
            nativeQuery = true)
    public List<Object[]> obtenerSegurosPA(@Param("idPersona") int idPersona);

    // 
    @Query(value = "SELECT "+
                    "    i.idioma,"+
                    "    ni.nivel_idioma "+
                    "FROM idiomas i "+
                    "inner join solicitantes_idiomas si on i.ID_IDIOMA = si.ID_IDIOMA "+
                    "INNER join nivel_idioma ni on si.ID_NIVEL_IDIOMA = ni.ID_NIVEL_IDIOMA "+
                    "WHERE si.ID_SOLICITANTE = :idPersona",
            nativeQuery = true)
    public List<Object[]> obtenerIdiomasPA(@Param("idPersona") int idPersona);

    //
    @Query(value = "SELECT "+
                    "    p.puesto,"+
                    "    el.empresa,"+
                    "    DATE_FORMAT(el.fecha_inicio, '%d %b, %Y') AS fechaInicio,"+
                    "    DATE_FORMAT(el.fecha_fin, '%d %b, %Y') AS fechaFin "+
                    "FROM experiencia_laboral el  "+
                    "INNER JOIN puestos p on el.ID_PUESTO = p.ID_PUESTO "+
                    "WHERE el.ID_PERSONA = :idPersona",
            nativeQuery = true)
    public List<Object[]> obtenerExperienciaLaboralPA(@Param("idPersona") int idPersona);

    //
    @Query(value = "SELECT "+
                    "    CONCAT(p.primer_nombre,' ',p.primer_apellido) as nombre,"+
                    "    p.identificacion,"+
                    "    CONCAT(p.telefono,'') as telefono, "+
                    "    pa.parentesco "+
                    "FROM personas p "+
                    "INNER JOIN familiares f on p.ID_PERSONA = f.ID_FAMILIAR "+
                    "INNER JOIN parentescos pa on f.ID_PARENTESCOS = pa.ID_PARENTESCOS "+
                    "WHERE f.ID_SOLICITANTE = :idPersona;",
            nativeQuery = true)
    public List<Object[]> obtenerFamiliaresPA(@Param("idPersona") int idPersona);
}
