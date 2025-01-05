package com.example.bp_project.controller;

import com.example.bp_project.service.ReporteService;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private ReporteService reporteService;

    public ReporteController(ReporteService reporteService){
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<Map<String, Object>> obtenerReporte(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        return reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
    }
}
