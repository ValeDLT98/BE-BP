package com.example.bp_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaDTO {

    private Long numero;
    private String tipo;
    private Double saldo;
    private Boolean estado;
    private String nombreCliente;

    public CuentaDTO(Long numero, String tipo, Double saldo, Boolean estado, String nombreCliente) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
