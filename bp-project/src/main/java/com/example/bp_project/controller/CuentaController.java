package com.example.bp_project.controller;

import com.example.bp_project.dto.CuentaDTO;
import com.example.bp_project.entity.Cuenta;
import com.example.bp_project.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping("/all")
    public List<Cuenta> getAllCuentas(){
        return cuentaService.getAllCuentas();
    }

    @GetMapping
    public List<CuentaDTO> getCuentas (){
        return cuentaService.getCuentas();
    }

    @PostMapping
    public Cuenta saveCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.saveCuenta(cuenta);
    }

    @GetMapping("/{id}")
    public Optional<Cuenta> getById(@PathVariable Long id){
        return cuentaService.searchById(id);
    }

    @PutMapping("/{id}")
    public Cuenta editCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta){
        return cuentaService.editCuenta(id, cuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCuenta(@PathVariable Long id){
        boolean eliminado = cuentaService.deleteCuenta(id);
        Map<String, String> response = new HashMap<>();

        if (eliminado) {
            response.put("message", "Cuenta eliminada correctamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Cuenta no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
