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

}
