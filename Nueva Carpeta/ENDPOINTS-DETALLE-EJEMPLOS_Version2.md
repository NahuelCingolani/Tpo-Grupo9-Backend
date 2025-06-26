# Endpoints de la API de Ecommerce: Descripción y Ejemplos

A continuación se detallan los endpoints principales de la API, su finalidad, cómo funcionan y ejemplos de requests/responses típicos.

---

## 1. Autenticación

### POST `/api/auth/register`
**Motivo:** Registrar un nuevo usuario (crear cuenta).

**Request:**
```json
POST /api/auth/register
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Ramirez",
  "email": "juan@correo.com",
  "password": "12345678",
  "role": "USER"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

---

### POST `/api/auth/login`
**Motivo:** Iniciar sesión con usuario registrado.

**Request:**
```json
POST /api/auth/login
Content-Type: application/json

{
  "email": "juan@correo.com",
  "password": "12345678"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

---

## 2. Productos

### GET `/api/productos`
**Motivo:** Listar todos los productos disponibles.

**Request:**
```
GET /api/productos
```

**Response (200 OK):**
```json
[
  {
    "id": "65d1e23a1b...",
    "nombre": "Camiseta LeBron Lakers City Edition",
    "descripcion": "Camiseta oficial City Edition de LeBron James",
    "precio": 75000,
    "equipo": "Lakers",
    "jugador": "LeBron James",
    "categoria": "City Edition",
    "stockPorTalle": { "S": 1, "M": 4, "L": 2, "XL": 1 }
  },
  ...
]
```

---

### GET `/api/productos/{id}`
**Motivo:** Obtener un producto específico por id.

**Request:**
```
GET /api/productos/65d1e23a1b...
```

**Response (200 OK):**
```json
{
  "id": "65d1e23a1b...",
  "nombre": "Camiseta LeBron Lakers City Edition",
  "descripcion": "Camiseta oficial City Edition de LeBron James",
  "precio": 75000,
  "equipo": "Lakers",
  "jugador": "LeBron James",
  "categoria": "City Edition",
  "stockPorTalle": { "S": 1, "M": 4, "L": 2, "XL": 1 }
}
```

---

### POST `/api/productos`
**Motivo:** Crear un nuevo producto.

**Request:**
```json
POST /api/productos
Authorization: Bearer <token-admin>
Content-Type: application/json

{
  "nombre": "Gorra Lakers",
  "descripcion": "Gorra oficial Lakers",
  "precio": 25000,
  "equipo": "Lakers",
  "jugador": "",
  "categoria": "Gorras",
  "stockPorTalle": { "ÚNICO": 5 }
}
```

**Response (200 OK):**
```json
{
  "id": "65d1e23a1b...", #OBJECT ID - Generado x MongoDB
  "nombre": "Gorra Lakers",
  "descripcion": "Gorra oficial Lakers",
  "precio": 25000,
  "equipo": "Lakers",
  "jugador": "",
  "categoria": "Gorras",
  "stockPorTalle": { "ÚNICO": 5 }
}
```

---

### PUT `/api/productos/{id}`
**Motivo:** Actualizar completamente un producto.

**Request:**
```json
PUT /api/productos/65d1e23a1b...
Authorization: Bearer <token-admin>
Content-Type: application/json

{
  "nombre": "Gorra Lakers Actualizada",
  "descripcion": "Nueva descripción",
  "precio": 30000,
  "equipo": "Lakers",
  "jugador": "",
  "categoria": "Gorras",
  "stockPorTalle": { "ÚNICO": 10 }
}
```

**Response (200 OK):**
```json
{
  "id": "65d1e23a1b...",
  "nombre": "Gorra Lakers Actualizada",
  "descripcion": "Nueva descripción",
  "precio": 30000,
  "equipo": "Lakers",
  "jugador": "",
  "categoria": "Gorras",
  "stockPorTalle": { "ÚNICO": 10 }
}
```

---

### PATCH `/api/productos/{id}`
**Motivo:** Actualizar parcialmente un producto (solo ciertos campos).

**Request:**
```json
PATCH /api/productos/65d1e23a1b...
Authorization: Bearer <token-admin>
Content-Type: application/json

{
  "precio": 35000
}
```

**Response (200 OK):**
```json
{
  "id": "65d1e23a1b...",
  "nombre": "Gorra Lakers Actualizada",
  "descripcion": "Nueva descripción",
  "precio": 35000,
  "equipo": "Lakers",
  "jugador": "",
  "categoria": "Gorras",
  "stockPorTalle": { "ÚNICO": 10 }
}
```

---

### DELETE `/api/productos/{id}`
**Motivo:** Eliminar un producto por id.

**Request:**
```
DELETE /api/productos/65d1e23a1b...
Authorization: Bearer <token-admin>
```

**Response (200 OK):**
```json
"Producto eliminado correctamente."
```

---

### POST `/api/productos/importarMock`
**Motivo:** Importar productos de ejemplo/mock.

**Request:**
```
POST /api/productos/importarMock
Authorization: Bearer <token-admin>
```

**Response (200 OK):**
```json
"1 productos nuevos agregados."
```

---

### DELETE `/api/productos/eliminarTodos`
**Motivo:** Eliminar todos los productos de la base de datos.

**Request:**
```
DELETE /api/productos/eliminarTodos
Authorization: Bearer <token-admin>
```

**Response (200 OK):**
```json
"Todos los productos fueron eliminados."
```

---

### GET `/api/productos/equipo/{equipo}`
**Motivo:** Listar todos los productos de un equipo.

**Request:**
```
GET /api/productos/equipo/Lakers
```

**Response (200 OK):**
```json
[
  {
    "id": "65d1e23a1b...",
    "nombre": "Gorra Lakers",
    "descripcion": "Gorra oficial Lakers",
    "precio": 25000,
    "equipo": "Lakers",
    "jugador": "",
    "categoria": "Gorras",
    "stockPorTalle": { "ÚNICO": 5 }
  }
]
```

---

## 3. Carrito

### POST `/api/carrito`
**Motivo:** Crear un carrito para un usuario.

**Request:**
```json
POST /api/carrito
Authorization: Bearer <token>
Content-Type: application/json

{
  "usuarioId": "65d1e2a1b4...",
  "items": [
    {
      "productoId": "65d1e23a1b...",
      "cantidad": 2,
      "talle": "M"
    }
  ]
}
```

**Response (200 OK):**
```json
{
  "id": "65d1e2a1bc...",
  "usuarioId": "65d1e2a1b4...",
  "items": [
    {
      "productoId": "65d1e23a1b...",
      "cantidad": 2,
      "talle": "M"
    }
  ]
}
```

---

### GET `/api/carrito/{usuarioId}`
**Motivo:** Obtener el carrito de un usuario.

**Request:**
```
GET /api/carrito/65d1e2a1b4...
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
{
  "id": "65d1e2a1bc...",
  "usuarioId": "65d1e2a1b4...",
  "items": [
    {
      "productoId": "65d1e23a1b...",
      "cantidad": 2,
      "talle": "M"
    }
  ]
}
```

---

### PUT `/api/carrito/actualizar`
**Motivo:** Actualizar el carrito del usuario.

**Request:**
```json
PUT /api/carrito/actualizar
Authorization: Bearer <token>
Content-Type: application/json

{
  "usuarioId": "65d1e2a1b4...",
  "items": [
    {
      "productoId": "65d1e23a1b...",
      "cantidad": 1,
      "talle": "M"
    }
  ]
}
```

**Response (200 OK):**
```json
"Carrito actualizado."
```

---

### DELETE `/api/carrito/vaciar/{usuarioId}`
**Motivo:** Vaciar el carrito del usuario.

**Request:**
```
DELETE /api/carrito/vaciar/65d1e2a1b4...
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
"Carrito vaciado."
```

---

## Notas sobre los mecanismos

- **Autenticación:** Los endpoints protegidos requieren el header `Authorization: Bearer <token>`, que se obtiene en login/registro.
- **Roles:** Algunos endpoints solo pueden ser usados por admins (`role: ADMIN`), otros por usuarios autenticados (`role: USER`).
- **Validaciones:** El backend valida stock, campos obligatorios y formato en cada operación.

---