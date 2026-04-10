# ECIXPRESS - Gestión de Pedidos en Cafeterías

## Información del Proyecto
- **Autor:** Juan David Valero y Carlos Andres Uribe Vargas
- **Grupo:** [1]
- **Versión de Java:** 21
- **Framework:** Spring Boot 3.1.5
- **Base de Datos:** MySQL 8.0
- **Descripción:** MVP de aplicación web para gestión de pedidos en cafeterías institucionales mediante códigos QR

---

## Especificación de Funcionalidades

### Funcionalidades Identificadas - Análisis de Requisitos REST API

![Punto 1 - Análisis de Funcionalidades](docs/images/Punto_1.png)

---

## Ejemplos JSON 

### 1. Registro de Usuario

**Entrada (POST /auth/registro):**
```json
{
  "nombre": "Juan García López",
  "email": "juan.garcia@institucion.edu.co",
  "password": "Segura2024!",
  "rol": "CLIENTE"
}
```

**Salida 201 Created:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nombre": "Juan García López",
  "email": "juan.garcia@institucion.edu.co",
  "rol": "CLIENTE",
  "fechaRegistro": "2026-04-10T14:30:00Z",
  "estado": "ACTIVO"
}
```

**Error 409 Conflict:**
```json
{
  "error": "Conflict",
  "mensaje": "El email juan.garcia@institucion.edu.co ya está registrado",
  "timestamp": "2026-04-10T14:30:00Z"
}
```

---

### 2. Autenticación (Login)

**Entrada (POST /auth/login):**
```json
{
  "email": "juan.garcia@institucion.edu.co",
  "password": "Segura2024!"
}
```

**Salida 200 OK:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTBlODQwMC1lMjliLTQxZDQtYTcxNi00NDY2NTU0NDAwMDAiLCJpYXQiOjE3MTI3NTAwMDAsImV4cCI6MTcxMjgzNjQwMH0.signature",
  "tipo": "Bearer",
  "usuario": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "nombre": "Juan García López",
    "email": "juan.garcia@institucion.edu.co",
    "rol": "CLIENTE"
  },
  "expiresIn": 86400
}
```

---

### 3. Consultar Producto por QR

**Entrada (GET /productos/qr?codigoQR=PROD123456789):**
```json
{
  "codigoQR": "PROD123456789"
}
```

**Salida 200 OK:**
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440001",
  "nombre": "Café Cappuccino",
  "descripcion": "Bebida caliente preparada con espresso y leche vaporizada",
  "precio": 4500.00,
  "codigoQR": "PROD123456789",
  "stockDisponible": 45,
  "estado": "DISPONIBLE"
}
```

---

### 4. Crear Pedido

**Entrada (POST /pedidos):**
```json
{
  "usuarioId": "550e8400-e29b-41d4-a716-446655440000",
  "items": [
    {
      "productoId": "660e8400-e29b-41d4-a716-446655440001",
      "cantidad": 2
    },
    {
      "productoId": "770e8400-e29b-41d4-a716-446655440002",
      "cantidad": 1
    }
  ],
  "notas": "Café sin azúcar"
}
```

**Salida 201 Created:**
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440000",
  "usuarioId": "550e8400-e29b-41d4-a716-446655440000",
  "estado": "CREADO",
  "items": [
    {
      "productoId": "660e8400-e29b-41d4-a716-446655440001",
      "nombre": "Café Cappuccino",
      "cantidad": 2,
      "precioUnitario": 4500.00,
      "subtotal": 9000.00
    },
    {
      "productoId": "770e8400-e29b-41d4-a716-446655440002",
      "nombre": "Croissant",
      "cantidad": 1,
      "precioUnitario": 3500.00,
      "subtotal": 3500.00
    }
  ],
  "total": 12500.00,
  "notas": "Café sin azúcar",
  "fechaCreacion": "2026-04-10T15:00:00Z"
}
```

---

### 5. Cambiar Estado Pedido

**Entrada (PATCH /pedidos/880e8400-e29b-41d4-a716-446655440000/estado):**
```json
{
  "pedidoId": "880e8400-e29b-41d4-a716-446655440000",
  "nuevoEstado": "EN_PREPARACION",
  "notas": "Preparando el pedido"
}
```

**Salida 200 OK:**
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440000",
  "usuarioId": "550e8400-e29b-41d4-a716-446655440000",
  "estado": "EN_PREPARACION",
  "estadoAnterior": "CREADO",
  "items": [...],
  "total": 12500.00,
  "notas": "Preparando el pedido",
  "fechaUltimaActualizacion": "2026-04-10T15:05:00Z",
  "actualizadoPor": "cafeteria@institucion.edu.co"
}
```

---

### 6. Cancelar Pedido

**Entrada (PATCH /pedidos/880e8400-e29b-41d4-a716-446655440000/cancelar):**
```json
{
  "pedidoId": "880e8400-e29b-41d4-a716-446655440000",
  "razon": "El cliente cambió de opinión"
}
```

**Salida 200 OK:**
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440000",
  "usuarioId": "550e8400-e29b-41d4-a716-446655440000",
  "estado": "CANCELADO",
  "estadoAnterior": "CREADO",
  "total": 12500.00,
  "razon": "El cliente cambió de opinión",
  "stockRecuperado": 3,
  "fechaCancelacion": "2026-04-10T16:00:00Z"
}
```

---

### 7. Confirmar Pedido (Actualizar Stock)

**Entrada (PATCH /pedidos/880e8400-e29b-41d4-a716-446655440000/confirmar):**
```json
{
  "pedidoId": "880e8400-e29b-41d4-a716-446655440000",
  "metodoPago": "TARJETA"
}
```

**Salida 200 OK:**
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440000",
  "usuarioId": "550e8400-e29b-41d4-a716-446655440000",
  "estado": "EN_PREPARACION",
  "metodoPago": "TARJETA",
  "stockActualizado": [
    {
      "productoId": "660e8400-e29b-41d4-a716-446655440001",
      "nombre": "Café Cappuccino",
      "cantidadAnterior": 45,
      "cantidadNueva": 43
    },
    {
      "productoId": "770e8400-e29b-41d4-a716-446655440002",
      "nombre": "Croissant",
      "cantidadAnterior": 30,
      "cantidadNueva": 29
    }
  ],
  "total": 12500.00,
  "fechaConfirmacion": "2026-04-10T15:10:00Z"
}
```

---

## Matriz de Acceso por Rol

## Matriz de Acceso por Rol
![Punto 1 - Matriz de Acceso por Rol](docs/images/Punto_1.2.png)


### Ejecutar el Proyecto
```bash
mvn clean install
mvn spring-boot:run
```
