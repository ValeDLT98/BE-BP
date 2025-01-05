package com.example.bp_project.service;

import com.example.bp_project.dto.CuentaDTO;
import com.example.bp_project.entity.Cliente;
import com.example.bp_project.entity.Cuenta;
import com.example.bp_project.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCuentas() {
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumero(12345L);
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumero(67890L);

        when(cuentaRepository.findAll()).thenReturn(List.of(cuenta1, cuenta2));

        List<Cuenta> cuentas = cuentaService.getAllCuentas();

        assertEquals(2, cuentas.size());
        assertEquals(12345L, cuentas.get(0).getNumero());
        assertEquals(67890L, cuentas.get(1).getNumero());
        verify(cuentaRepository, times(1)).findAll();
    }

    @Test
    void testGetCuentas() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Nombre Apellido");

        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(12345L);
        cuenta.setTipo("Corriente");
        cuenta.setSaldo(1000.0);
        cuenta.setEstado(true);
        cuenta.setCliente(cliente);

        when(cuentaRepository.findAll()).thenReturn(List.of(cuenta));

        List<CuentaDTO> cuentaDTOs = cuentaService.getCuentas();

        assertEquals(1, cuentaDTOs.size());
        CuentaDTO cuentaDTO = cuentaDTOs.get(0);
        assertEquals(12345L, cuentaDTO.getNumero());
        assertEquals("Corriente", cuentaDTO.getTipo());
        assertEquals(1000.0, cuentaDTO.getSaldo());
        assertTrue(cuentaDTO.getEstado());
        assertEquals("Nombre Apellido", cuentaDTO.getNombreCliente());
        verify(cuentaRepository, times(1)).findAll();
    }

    @Test
    void testSaveCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(12345L);

        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);

        Cuenta savedCuenta = cuentaService.saveCuenta(cuenta);

        assertNotNull(savedCuenta);
        assertEquals(12345L, savedCuenta.getNumero());
        verify(cuentaRepository, times(1)).save(cuenta);
    }

    @Test
    void testSearchById() {
        Long cuentaId = 1L;
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaId);

        when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));
        
        Optional<Cuenta> foundCuenta = cuentaService.searchById(cuentaId);

        assertTrue(foundCuenta.isPresent());
        assertEquals(cuentaId, foundCuenta.get().getNumero());
        verify(cuentaRepository, times(1)).findById(cuentaId);
    }

    @Test
    void testEditCuenta() {
        Long cuentaId = 1L;
        Cuenta cuentaExistente = new Cuenta();
        cuentaExistente.setNumero(cuentaId);

        Cuenta cuentaNueva = new Cuenta();
        cuentaNueva.setNumero(12345L);
        cuentaNueva.setTipo("Ahorro");
        cuentaNueva.setSaldo(500.0);
        cuentaNueva.setEstado(false);

        when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuentaExistente));
        when(cuentaRepository.save(cuentaExistente)).thenReturn(cuentaExistente);

        Cuenta updatedCuenta = cuentaService.editCuenta(cuentaId, cuentaNueva);

        assertNotNull(updatedCuenta);
        assertEquals(12345L, updatedCuenta.getNumero());
        assertEquals("Ahorro", updatedCuenta.getTipo());
        assertEquals(500.0, updatedCuenta.getSaldo());
        assertFalse(updatedCuenta.getEstado());
        verify(cuentaRepository, times(1)).findById(cuentaId);
        verify(cuentaRepository, times(1)).save(cuentaExistente);
    }

    @Test
    void testDeleteCuentaExists() {
        Long cuentaId = 1L;
        when(cuentaRepository.existsById(cuentaId)).thenReturn(true);

        boolean result = cuentaService.deleteCuenta(cuentaId);

        assertTrue(result);
        verify(cuentaRepository, times(1)).existsById(cuentaId);
        verify(cuentaRepository, times(1)).deleteById(cuentaId);
    }

    @Test
    void testDeleteCuentaApellidosNotExist() {
        Long cuentaId = 1L;
        when(cuentaRepository.existsById(cuentaId)).thenReturn(false);

        boolean result = cuentaService.deleteCuenta(cuentaId);
        
        assertFalse(result);
        verify(cuentaRepository, times(1)).existsById(cuentaId);
        verify(cuentaRepository, never()).deleteById(cuentaId);
    }
}
