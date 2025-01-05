package com.example.bp_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "tipo_movimiento")
    private String tipo_movimiento;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "saldo_disponible")
    private Double saldo_disponible;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta")
    private Cuenta cuenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getSaldo_disponible() {
        return saldo_disponible;
    }

    public void setSaldo_disponible(Double saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
