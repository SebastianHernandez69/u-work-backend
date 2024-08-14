package com.example.proyecto_analisis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.proyecto_analisis.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
    @Query("SELECT e FROM Empresa e WHERE correo = ?1 AND contrasena = ?2")
    public Empresa autenticarDirectorEmpresa(String correo, String contrasena);
}
