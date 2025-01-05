package com.example.bp_project.dto;

import com.example.bp_project.entity.Cuenta;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovimientoDTO {

    private Long id;
    private String tipo_movimiento;
    private Double monto;
    private Date fecha;
    private Double saldo_disponible;
    private Long cuenta;

    public MovimientoDTO(Long id, String tipo_movimiento, Double monto, Date fecha, Double saldo_disponible, Long cuenta) {
        this.id = id;
        this.tipo_movimiento = tipo_movimiento;
        this.monto = monto;
        this.fecha = fecha;
        this.saldo_disponible = saldo_disponible;
        this.cuenta = cuenta;
    }
}
