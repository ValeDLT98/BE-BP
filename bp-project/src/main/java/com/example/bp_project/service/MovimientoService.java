package com.example.bp_project.service;

import com.example.bp_project.dto.MovimientoDTO;
import com.example.bp_project.entity.Cuenta;
import com.example.bp_project.entity.Movimiento;
import com.example.bp_project.repository.CuentaRepository;
import com.example.bp_project.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository){
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public List<MovimientoDTO> getMovimientos(){
        List<Movimiento> movimientos = movimientoRepository.findAll();
        List<MovimientoDTO> movimientoDTOS = new ArrayList<>();

        for(Movimiento mov : movimientos){
            Long numero_cuenta = mov.getCuenta() != null ? mov.getCuenta().getNumero() : 0;

            MovimientoDTO movimientoDTO = new MovimientoDTO(
                    mov.getId(),
                    mov.getTipo_movimiento(),
                    mov.getMonto(),
                    mov.getFecha(),
                    mov.getSaldo_disponible(),
                    numero_cuenta
            );
            movimientoDTOS.add(movimientoDTO);
        }

        return movimientoDTOS;
    }


    public Movimiento crearMovimiento(Movimiento movimiento){

        Cuenta cuenta = movimiento.getCuenta();

        Double saldo_inicial = movimiento.getCuenta().getSaldo();

        Double saldo_disponible = 0.0;

        if(movimiento.getTipo_movimiento().equals("Retiro") ){
            movimiento.setMonto(movimiento.getMonto() * -1);
        }

        saldo_disponible = saldo_inicial + movimiento.getMonto();

        cuenta.setSaldo(saldo_disponible);
        cuentaRepository.save(cuenta);

        movimiento.setSaldo_disponible(saldo_disponible);


        return movimientoRepository.save(movimiento);
    }

    public Optional<Movimiento> getById(Long id){
        return movimientoRepository.findById(id);
    }
    public Movimiento editMovimiento(Long id, Movimiento movimiento){
        Movimiento movimientoExistente = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        Cuenta cuenta = movimiento.getCuenta();


        Double saldo_inicial = cuenta.getSaldo();

        Double saldo_disponible = 0.0;

        if(movimiento.getTipo_movimiento().equals("Retiro") ){
            movimiento.setMonto(movimiento.getMonto() * -1);
        }

        saldo_disponible = saldo_inicial + movimiento.getMonto();

        cuenta.setSaldo(saldo_disponible);
        cuentaRepository.save(cuenta);

        movimientoExistente.setSaldo_disponible(saldo_disponible);

        movimientoExistente.setTipo_movimiento(movimiento.getTipo_movimiento());
        movimientoExistente.setFecha(movimiento.getFecha());
        movimientoExistente.setMonto(movimiento.getMonto());
        movimientoExistente.setCuenta(movimiento.getCuenta());

        return movimientoRepository.save(movimientoExistente);
    }

    public boolean deleteMovimiento(Long id){
        if(movimientoRepository.existsById(id)){
            movimientoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
