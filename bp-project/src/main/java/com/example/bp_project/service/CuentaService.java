package com.example.bp_project.service;

import com.example.bp_project.dto.CuentaDTO;
import com.example.bp_project.entity.Cuenta;
import com.example.bp_project.repository.CuentaRepository;
import com.example.bp_project.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public CuentaService(CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository){
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public List<Cuenta> getAllCuentas(){
        return cuentaRepository.findAll();
    }

    public List<CuentaDTO> getCuentas(){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaDTO> cuentaDTOs = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            String nombreCliente = cuenta.getCliente() != null ? cuenta.getCliente().getNombres() : "Desconocido";

            CuentaDTO cuentaDTO = new CuentaDTO(
                    cuenta.getNumero(),
                    cuenta.getTipo(),
                    cuenta.getSaldo(),
                    cuenta.getEstado(),
                    nombreCliente
            );
            cuentaDTOs.add(cuentaDTO);
        }

        return cuentaDTOs;
    }

    public Cuenta saveCuenta(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public Optional<Cuenta> searchById(Long id){
        return cuentaRepository.findById(id);
    }

    public Cuenta editCuenta(Long id, Cuenta cuenta){
        Cuenta cuentaExistente = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuentaExistente.setNumero(cuenta.getNumero());
        cuentaExistente.setTipo(cuenta.getTipo());
        cuentaExistente.setEstado(cuenta.getEstado());
        cuentaExistente.setSaldo(cuenta.getSaldo());
        cuentaExistente.setCliente(cuenta.getCliente());

        return cuentaRepository.save(cuentaExistente);

    }

    public boolean deleteCuenta(Long id){
        if(cuentaRepository.existsById(id)){
            cuentaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
