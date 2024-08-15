package com.example.proyecto_analisis.repository;

import java.util.Date;
import java.util.List;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Solicitante;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer> {
    
    @Query("SELECT s FROM Solicitante s WHERE correo = ?1 AND contrasena = ?2")
    public Solicitante autenticarSolicitante(String correo, String contrasena);

    @Modifying
    @Transactional
    @Query(value = "CALL insertarHistorialAcademico(:idPersonaP, :idNivelAcademicoP, :idFormacionAcP, :tituloP, :fechaEgresoP, :INSTITUCIONP)", nativeQuery = true)
    void insertarHistorialAcademico(
        @Param("idPersonaP") int idPersonaP, 
        @Param("idNivelAcademicoP") int idNivelAcademicoP, 
        @Param("idFormacionAcP") int idFormacionAcP, 
        @Param("tituloP") String tituloP, 
        @Param("fechaEgresoP") Date fechaEgresoP, 
        @Param("INSTITUCIONP") String INSTITUCIONP
    );

    @Modifying
    @Transactional
    @Query(value = "CALL ingresarSolicitanteIdioma(:idPersonaP, :idIdiomaP, :idNivelP)", nativeQuery = true)
    public void ingresarSolicitanteIdioma(
        @Param("idPersonaP") int idPersonaP,
        @Param("idIdiomaP") int idIdiomaP,
        @Param("idNivelP") int idNivelP
    );

    @Modifying
    @Transactional
    @Query(value = "CALL insertarSeguroSolicitante(:idPersonaP, :idTipoSeguroP, :fechaAfiliacionP, :fechaExpiracionP, :numeroAfiliacionP)", nativeQuery = true)
    public void insertarSeguroSolicitante(
        @Param("idPersonaP") int idPersonaP,
        @Param("idTipoSeguroP") int idTipoSeguroP,
        @Param("fechaAfiliacionP") Date fechaAfiliacionP,
        @Param("fechaExpiracionP") Date fechaExpiracionP,
        @Param("numeroAfiliacionP") String numeroAfiliacionP
    );

    @Modifying
    @Transactional
    @Query(value = "CALL ingresarFamiliarSolicitante(:idFamiliarP, :idParentescoP, :idSolicitante)", nativeQuery = true)
    public void ingresarFamiliarSolicitante(
        @Param("idFamiliarP") int idFamiliarP,
        @Param("idParentescoP") int idParentescoP,
        @Param("idSolicitante") int idSolicitante
    );

    @Modifying
    @Transactional
    @Query(value = "CALL ingresarHistorialMedico(:descripcionP, :idCondicionP, :idPersona)", nativeQuery = true)
    public void ingresarHistorialMedico(
        @Param("descripcionP") String descripcionP,
        @Param("idCondicionP") int idCondicionP,
        @Param("idPersona") int idPersona
    );

    //Obtener aplicaciones del solicitante
    @Query(value = "CALL obtenerAplicacionesSolicitante(:idSolicitanteP)", nativeQuery = true)
    public List<Object[]> obtenerAplicacionesSolicitante(@Param("idSolicitanteP") int idSolicitanteP);


    //Obtener detalle de notificion
    @Query(value = "SELECT "+
                    "    ns.id_notificacion_sol,"+
                    "    ns.titulo,"+
                    "    em.url_logo,"+
                    "    em.nombre_empresa,"+
                    "    ns.descripcion,"+
                    "    DATE_FORMAT(ns.fecha, '%d %b, %Y') as fechaEnvio,"+
                    "    ns.id_solicitud "+
                    "FROM notificaciones_solicitantes ns "+
                    "INNER JOIN solicitudes s on ns.ID_SOLICITUD = s.ID_SOLICITUD "+
                    "INNER JOIN ofertas of on s.ID_OFERTA = of.ID_OFERTA "+
                    "INNER JOIN empresa em on of.ID_EMPRESA = em.ID_EMPRESA "+
                    "WHERE ns.ID_NOTIFICACION_SOL = :idNotifSolicitanteP;", nativeQuery = true
                    )
    public List<Object[]> obtenerDetalleNotificacionSolic(@Param("idNotifSolicitanteP") int idNotifSolicitanteP);

    // Cambiar estado de visualizacion a notificacion
    @Modifying
    @Transactional
    @Query(value = "UPDATE `notificaciones_solicitantes` "+
                   "SET ESTADO_VISUALIZACION = 0 "+
                   "WHERE ID_NOTIFICACION_SOL = :idNotifSolicitanteP", nativeQuery = true)
    public void cambiarEstadoNotiSolic(@Param("idNotifSolicitanteP") int idNotifSolicitanteP);


    @Query(value = "(\r\n" + //
                "  SELECT A.ID_OFERTA, A.FECHA_PUBLICACION, A.TITULO, E.NOMBRE_EMPRESA, A.DESCRIPCION, E.URL_LOGO\r\n" + //
                "  FROM ofertas AS A\r\n" + //
                "  INNER JOIN requisitos_academicos AS B ON (A.ID_OFERTA = B.ID_OFERTA)\r\n" + //
                "  INNER JOIN formacion_profesional AS C ON (B.ID_FORMACION_PROFESIONAL = C.ID_FORMACION_PROFESIONAL)\r\n" + //
                "  INNER JOIN empresa AS E ON (E.ID_EMPRESA = A.ID_EMPRESA)\r\n" + //
                "  INNER JOIN historial_academico AS D ON (C.ID_FORMACION_PROFESIONAL = D.ID_FORMACION_PROFESIONAL)\r\n" + //
                "  WHERE D.ID_PERSONA = :idPersona\r\n" + //
                ")\r\n" + //
                "UNION\r\n" + //
                "(\r\n" + //
                "  SELECT A.ID_OFERTA, A.FECHA_PUBLICACION, A.TITULO, E.NOMBRE_EMPRESA, A.DESCRIPCION, E.URL_LOGO\r\n" + //
                "  FROM ofertas AS A\r\n" + //
                "  INNER JOIN empresa AS E ON (E.ID_EMPRESA = A.ID_EMPRESA)\r\n" + //
                "  INNER JOIN requisitos_laborales AS B ON (A.ID_OFERTA = B.ID_OFERTA)\r\n" + //
                "  INNER JOIN puestos AS C ON (B.ID_PUESTO = C.ID_PUESTO)\r\n" + //
                "  INNER JOIN EXPERIENCIA_LABORAL AS D ON (C.ID_PUESTO = D.ID_PUESTO)\r\n" + //
                "  WHERE D.ID_PERSONA = :idPersona\r\n" + //
                ")\r\n" + //
                "UNION\r\n" + //
                "(\r\n" + //
                "  SELECT A.ID_OFERTA, A.FECHA_PUBLICACION, A.TITULO, E.NOMBRE_EMPRESA, A.DESCRIPCION, E.URL_LOGO\r\n" + //
                "  FROM ofertas AS A\r\n" + //
                "  INNER JOIN empresa AS E ON (E.ID_EMPRESA = A.ID_EMPRESA)\r\n" + //
                "  INNER JOIN NIVEL_ACADEMICO AS B ON (A.ID_NIVEL_ACADEMICO = B.ID_NIVEL_ACADEMICO)\r\n" + //
                "  INNER JOIN HISTORIAL_ACADEMICO AS C ON (B.ID_NIVEL_ACADEMICO = C.ID_NIVEL_ACADEMICO)\r\n" + //
                "  WHERE C.ID_PERSONA = :idPersona\r\n" + //
                ")\r\n" + //
                "LIMIT 10 OFFSET 0;\r\n" + //
                "",
            nativeQuery = true)
    public List<Object[]> obtenerOfertasFeedUsuario(@Param("idPersona") int idPersona);
}
