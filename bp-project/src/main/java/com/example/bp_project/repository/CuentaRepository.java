package com.example.bp_project.repository;

import com.example.bp_project.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
