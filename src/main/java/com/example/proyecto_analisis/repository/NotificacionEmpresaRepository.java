package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Empresa;

public interface NotificacionEmpresaRepository extends JpaRepository<Empresa,Integer> {
    
    @Query(value = "SELECT ne.ID_NOTIFICACION_EMP,"+
                    "        ne.TITULO,"+
                    "        em.NOMBRE_EMPRESA,"+
                    "        em.URL_LOGO,"+
                    "        ne.DESCRIPCION,"+
                    "        ne.FECHA,"+
                    "        ne.ID_SOLICITUD "+
                    "FROM notificaciones_empresas ne "+
                    "INNER JOIN empresa em on ne.ID_EMPRESA = em.ID_EMPRESA "+
                    "WHERE ne.ID_NOTIFICACION_EMP = :idNotificacionP;", nativeQuery = true)
    public Object[] obtenerNotificacionEmpresa(@Param("idNotificacionP") int idNotificacionP);

}
