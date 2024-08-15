package com.example.proyecto_analisis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Puesto;

public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
    
    @Query(value = "SELECT "+
                    "    p.id_puesto as id,"+
                    "    p.puesto "+
                    "FROM puestos p "+
                    "INNER JOIN preferencias_puestos pp on p.ID_PUESTO = pp.ID_PUESTO "+
                    "WHERE pp.ID_PERSONA = :idPersona;",
            nativeQuery = true)
    public List<Object[]> obtenerPreferenciaPuestos(@Param("idPersona") int idPersona);

    @Query(value = "SELECT "+
                    "    m.id_modalidad as id,"+
                    "    m.modalidad "+
                    "FROM modalidad m "+
                    "INNER JOIN preferencias_modalidades pm "+
                    "ON m.ID_MODALIDAD = pm.ID_MODALIDAD "+
                    "WHERE pm.ID_PERSONA = :idPersona;",
            nativeQuery = true)
    public List<Object[]> obtenerPreferenciaModalidad(@Param("idPersona") int idPersona);

    @Query(value = "SELECT "+
                    "    c.id_contrato as id,"+
                    "    c.contrato "+
                    "FROM contratos c "+
                    "INNER JOIN preferencias_contratos pc "+
                    "ON c.ID_CONTRATO = pc.ID_CONTRATO "+
                    "WHERE pc.ID_PERSONA = :idPersona",
            nativeQuery = true)
    public List<Object[]> obtenerPreferenciaContrato(@Param("idPersona") int idPersona);

    @Modifying
    @Query(value = "CALL eliminarPreferenciaSolicitante(:idPersona)", nativeQuery = true)
    public void eliminarPreferenciasUsuario(@Param("idPersona") int idPersona);

    @Modifying
    @Query(value = "CALL ingresarPreferenciaPuesto(:idPuestoP,:idPersonaP)", nativeQuery = true)
    public void ingresarPreferenciaPuesto(@Param("idPuestoP") int idPuestoP, @Param("idPersonaP") int idPersonaP);
    
    @Modifying
    @Query(value = "CALL ingresarPreferenciaModalidad(:idModalidadP,:idPersonaP)", nativeQuery = true)
    public void ingresarPreferenciaModalidad(@Param("idModalidadP") int idModalidadP, @Param("idPersonaP") int idPersonaP);
    
    @Modifying
    @Query(value = "CALL ingresarPreferenciaContrato(:idPersonaP, :idContratoP)", nativeQuery = true)
    public void ingresarPreferenciaContrato(@Param("idPersonaP") int idPersonaP, @Param("idContratoP") int idContratoP);
    
}
