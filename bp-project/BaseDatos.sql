-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS bancodb;

USE bancodb;

-- Crear tabla Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    contrasena VARCHAR(50),
    estado BOOLEAN,
    genero VARCHAR(50),
    edad INT,
    identificacion VARCHAR(255)
);

-- Crear tabla Cuentas con eliminación en cascada cuando se elimina un cliente
CREATE TABLE IF NOT EXISTS Cuentas (
    numero_cuenta INT PRIMARY KEY,
    tipo VARCHAR(50),
    saldo DECIMAL(10, 2),
    estado BOOLEAN,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id) ON DELETE CASCADE
);

-- Crear tabla Movimientos con eliminación en cascada cuando se elimina una cuenta
CREATE TABLE IF NOT EXISTS Movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta INT,
    tipo_movimiento VARCHAR(50),
    monto DECIMAL(10, 2),
    fecha DATE,
    saldo_disponible DECIMAL(10, 2),
    FOREIGN KEY (numero_cuenta) REFERENCES Cuentas(numero_cuenta) ON DELETE CASCADE
);
