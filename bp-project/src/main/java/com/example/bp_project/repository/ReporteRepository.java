package com.example.bp_project.repository;

import com.example.bp_project.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ReporteRepository extends JpaRepository<Movimiento, Long> {

    @Query(value = "SELECT DATE_FORMAT(m.fecha, '%d/%m/%Y') AS Fecha, " +
            "c.nombres AS Cliente, " +
            "cu.numero_cuenta AS NumeroCuenta, " +
            "cu.tipo AS Tipo, " +
            "cu.estado AS Estado, " +
            "m.monto AS Movimiento, " +
            "(m.saldo_disponible - m.monto) AS SaldoInicial, " +
            "m.saldo_disponible AS SaldoDisponible " +
            "FROM Clientes c " +
            "JOIN Cuentas cu ON c.id = cu.cliente_id " +
            "JOIN Movimientos m ON cu.numero_cuenta = m.numero_cuenta " +
            "WHERE c.id = :clienteId " +
            "AND m.fecha BETWEEN :fromDate AND :toDate", nativeQuery = true)
    List<Map<String, Object>> generarReporte(@Param("clienteId") Long clienteId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
