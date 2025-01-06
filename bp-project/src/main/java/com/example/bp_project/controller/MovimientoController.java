package com.example.bp_project.controller;

import com.example.bp_project.entity.Movimiento;
import com.example.bp_project.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService){
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public List<Movimiento> getMovimientos(){
        return movimientoService.getMovimientos();
    }

    @PostMapping
    public Movimiento crearMovimiento(@RequestBody Movimiento movimiento){
        return movimientoService.crearMovimiento(movimiento);
    }

    @GetMapping("/{id}")
    public Optional<Movimiento> getById(@PathVariable Long id){
        return movimientoService.getById(id);
    }

    @PutMapping("/{id}")
    public Movimiento editMovimiento (@PathVariable Long id, @RequestBody Movimiento movimiento){
        return movimientoService.editMovimiento(id, movimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMovimiento(@PathVariable Long id){
        boolean eliminado = movimientoService.deleteMovimiento(id);
        Map<String, String> response = new HashMap<>();

        if (eliminado) {
            response.put("message", "Movimiento eliminado correctamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Movimiento no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
