package com.example.bp_project.service;

import com.example.bp_project.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    private ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository){
        this.reporteRepository = reporteRepository;
    }

    public List<Map<String, Object>> generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        return reporteRepository.generarReporte(clienteId, fechaInicio, fechaFin);
    }
}
