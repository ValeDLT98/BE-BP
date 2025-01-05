package com.example.bp_project.service;

import com.example.bp_project.entity.Cliente;
import com.example.bp_project.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService (ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public boolean deleteCliente(Long id){
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;

    }

    public Optional<Cliente> getById(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente editCliente(Long id, Cliente cliente){
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));

        clienteExistente.setNombres(cliente.getNombres());
        clienteExistente.setGenero(cliente.getGenero());
        clienteExistente.setEdad(cliente.getEdad());
        clienteExistente.setIdentificacion(cliente.getIdentificacion());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setContrasena(cliente.getContrasena());
        clienteExistente.setEstado(cliente.getEstado());

        return clienteRepository.save(clienteExistente);
    }
}
