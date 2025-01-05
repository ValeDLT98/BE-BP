package com.example.bp_project.controller;

import com.example.bp_project.entity.Cliente;
import com.example.bp_project.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @PostMapping
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCliente(@PathVariable Long id) {
        boolean eliminado = clienteService.deleteCliente(id);
        Map<String, String> response = new HashMap<>();

        if (eliminado) {
            response.put("message", "Cliente eliminado correctamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Cliente no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getById(@PathVariable Long id){
        return clienteService.getById(id);
    }

    @PutMapping("/{id}")
    public Cliente editCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.editCliente(id, cliente);
    }
}
