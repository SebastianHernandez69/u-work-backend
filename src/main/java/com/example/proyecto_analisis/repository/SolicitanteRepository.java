package com.example.proyecto_analisis.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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


    @Query(value = "(\n" + //
                "  SELECT A.ID_OFERTA, DATE_FORMAT(A.FECHA_PUBLICACION, '%M %d, %Y') AS FECHA_PUBLICACION, A.TITULO, E.NOMBRE_EMPRESA, A.DESCRIPCION, E.URL_LOGO\n" + //
                "  FROM ofertas AS A\n" + //
                "  INNER JOIN requisitos_academicos AS B ON (A.ID_OFERTA = B.ID_OFERTA)\n" + //
                "  INNER JOIN formacion_profesional AS C ON (B.ID_FORMACION_PROFESIONAL = C.ID_FORMACION_PROFESIONAL)\n" + //
                "  INNER JOIN empresa AS E ON (E.ID_EMPRESA = A.ID_EMPRESA)\n" + //
                "  INNER JOIN historial_academico AS D ON (C.ID_FORMACION_PROFESIONAL = D.ID_FORMACION_PROFESIONAL)\n" + //
                "  WHERE D.ID_PERSONA = :idSolicitante\n" + //
                ")\n" + //
                "UNION\n" + //
                "(\n" + //
                "  SELECT A.ID_OFERTA, DATE_FORMAT(A.FECHA_PUBLICACION, '%M %d, %Y') AS FECHA_PUBLICACION, A.TITULO, E.NOMBRE_EMPRESA, A.DESCRIPCION, E.URL_LOGO\n" + //
                "  FROM ofertas AS A\n" + //
                "  INNER JOIN empresa AS E ON (E.ID_EMPRESA = A.ID_EMPRESA)\n" + //
                "  INNER JOIN requisitos_laborales AS B ON (A.ID_OFERTA = B.ID_OFERTA)\n" + //
                "  INNER JOIN puestos AS C ON (B.ID_PUESTO = C.ID_PUESTO)\n" + //
                "  INNER JOIN EXPERIENCIA_LABORAL AS D ON (C.ID_PUESTO = D.ID_PUESTO)\n" + //
                "  WHERE D.ID_PERSONA = :idSolicitante\n" + //
                ")\n" + //
                "UNION\n" + //
                "(\n" + //
                "  SELECT A.ID_OFERTA, DATE_FORMAT(A.FECHA_PUBLICACION, '%M %d, %Y') AS FECHA_PUBLICACION, A.TITULO, E.NOMBRE_EMPRESA, A.DESCRIPCION, E.URL_LOGO\n" + //
                "  FROM ofertas AS A\n" + //
                "  INNER JOIN empresa AS E ON (E.ID_EMPRESA = A.ID_EMPRESA)\n" + //
                "  INNER JOIN NIVEL_ACADEMICO AS B ON (A.ID_NIVEL_ACADEMICO = B.ID_NIVEL_ACADEMICO)\n" + //
                "  INNER JOIN HISTORIAL_ACADEMICO AS C ON (B.ID_NIVEL_ACADEMICO = C.ID_NIVEL_ACADEMICO)\n" + //
                "  WHERE C.ID_PERSONA = :idSolicitante\n" + //
                ")\n" + //
                "LIMIT 10 OFFSET 0;",
            nativeQuery = true)
    public List<Object[]> obtenerOfertasFeedUsuario(@Param("idSolicitante") int idPersona);

        //Obtener detalle de notificion
    @Query(value = "SELECT \n" + //
                    "    ID_NOTIFICACION_SOL, \n" + //
                    "    TITULO, \n" + //
                    "    DATE_FORMAT(FECHA, '%M %d, %Y') AS FECHA, \n" + //
                    "    ESTADO_VISUALIZACION,\n" + //
                    "    LEFT(DESCRIPCION, 30) AS DESCRIPCION\n" + //
                    "FROM NOTIFICACIONES_SOLICITANTES\n" + //
                    "WHERE ID_SOLICITANTE = :idSolicitante;", nativeQuery = true
    )
    public List<Object[]> obtenerVistaPrevNotificacion(@Param("idSolicitante") int idNotifSolicitanteP);

    //Obtener detalle de notificion
    @Query(value = "select * from tipo_empleo;", nativeQuery = true
    )
    public List<Object[]> obtenerCategorias();
}
