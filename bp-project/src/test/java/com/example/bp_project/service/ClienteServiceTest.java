package com.example.bp_project.service;

import com.example.bp_project.entity.Cliente;
import com.example.bp_project.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombres("Nombre Apellido");
        Cliente cliente2 = new Cliente();
        cliente2.setNombres("Nombre1 Apellido1");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.getClientes();

        assertEquals(2, clientes.size());
        assertEquals("Nombre Apellido", clientes.get(0).getNombres());
        assertEquals("Nombre1 Apellido1", clientes.get(1).getNombres());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testSaveCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Nombre Apellido");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.saveCliente(cliente);

        assertNotNull(savedCliente);
        assertEquals("Nombre Apellido", savedCliente.getNombres());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testDeleteClienteExists() {
        Long clienteId = 1L;
        when(clienteRepository.existsById(clienteId)).thenReturn(true);

        boolean result = clienteService.deleteCliente(clienteId);

        assertTrue(result);
        verify(clienteRepository, times(1)).existsById(clienteId);
        verify(clienteRepository, times(1)).deleteById(clienteId);
    }

    @Test
    void testDeleteClienteApellidosNotExist() {
        Long clienteId = 1L;
        when(clienteRepository.existsById(clienteId)).thenReturn(false);

        boolean result = clienteService.deleteCliente(clienteId);

        assertFalse(result);
        verify(clienteRepository, times(1)).existsById(clienteId);
        verify(clienteRepository, never()).deleteById(clienteId);
    }

    @Test
    void testGetById() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombres("Nombre Apellido");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        Optional<Cliente> foundCliente = clienteService.getById(clienteId);

        assertTrue(foundCliente.isPresent());
        assertEquals("Nombre Apellido", foundCliente.get().getNombres());
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void testEditCliente() {
        Long clienteId = 1L;
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(clienteId);
        clienteExistente.setNombres("Old Name");

        Cliente clienteNuevo = new Cliente();
        clienteNuevo.setNombres("New Name");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);

        Cliente updatedCliente = clienteService.editCliente(clienteId, clienteNuevo);

        assertNotNull(updatedCliente);
        assertEquals("New Name", updatedCliente.getNombres());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).save(clienteExistente);
    }
}
