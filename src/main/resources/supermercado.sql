create database supermercado;
use supermercado;
-- 1. Tabla Categoria
CREATE TABLE categoria (
    id bigint PRIMARY KEY,
    uuid_codigo VARCHAR(36) NOT NULL UNIQUE,
    nombre VARCHAR(255),
    descripción VARCHAR(255),
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notifield_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 2. Tabla Empleado
CREATE TABLE empleado (
    id bigint PRIMARY KEY,
    uuid_codigo VARCHAR(36) NOT NULL UNIQUE,
    nombre VARCHAR(255),
    cedula VARCHAR(255),
    cargo VARCHAR(50), -- Para el Enum (CAJERO, REPOSITOR, ADMINISTRADOR)
    salario DOUBLE PRECISION,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notifield_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 3. Tabla Producto
CREATE TABLE producto (
    id bigint PRIMARY KEY,
    uuid_codigo VARCHAR(36) NOT NULL UNIQUE,
    nombre VARCHAR(255),
    descripción VARCHAR(255),
    precio DOUBLE PRECISION,
    stock INTEGER,
    categoria_id BIGINT REFERENCES categoria(id),
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notifield_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 4. Tabla Proveedor
CREATE TABLE proveedor (
    id bigint PRIMARY KEY,
    nit VARCHAR(36) NOT NULL UNIQUE,
    nombre VARCHAR(255),
    dirección VARCHAR(255),
    telefono VARCHAR(255),
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notifield_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 5. Tabla Intermedia Producto_Proveedor (ManyToMany)
CREATE TABLE producto_proveedor (
    producto_id BIGINT REFERENCES producto(id),
    proveedor_id BIGINT REFERENCES proveedor(id),
    PRIMARY KEY (producto_id, proveedor_id)
);

-- 6. Tabla Venta
CREATE TABLE venta (
    id bigint PRIMARY KEY,
    uuid_codigo VARCHAR(36) NOT NULL UNIQUE,
    fecha TIMESTAMP,
    sub_total DOUBLE PRECISION,
    iva DOUBLE PRECISION,
    total DOUBLE PRECISION,
    empleado_id BIGINT REFERENCES empleado(id),
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notifield_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 7. Tabla DetalleVenta
CREATE TABLE detalle_venta (
    id bigint PRIMARY KEY,
    uuid_codigo VARCHAR(36) NOT NULL UNIQUE,
    cantidad INTEGER,
    precio_unitario DOUBLE PRECISION,
    venta_id BIGINT REFERENCES venta(id),
    producto_id BIGINT REFERENCES producto(id),
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);