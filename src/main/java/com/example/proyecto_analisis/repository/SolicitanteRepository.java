package com.example.proyecto_analisis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.proyecto_analisis.models.Solicitante;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer> {
    
    @Query("SELECT s FROM Solicitante s WHERE correo = ?1 AND contrasena = ?2")
    public Solicitante autenticarSolicitante(String correo, String contrasena);

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
