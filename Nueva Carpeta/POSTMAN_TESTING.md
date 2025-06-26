# Guía para probar el backend con Postman

Esta guía te ayudará a testear los endpoints principales del backend de ecommerce usando Postman.

## Endpoints disponibles

Todos los endpoints están bajo el path base:

```
http://localhost:9090/api/carrito
```

Asegúrate de tener el backend corriendo y la base de datos correctamente conectada antes de comenzar.

---

### 1. Crear un carrito

**Método:** POST  
**URL:** `http://localhost:9090/api/carrito`  
**Body (JSON):**
```json
{
  "usuarioId": "usuario1",
  "items": [
    {
      "productoId": "prod1",
      "cantidad": 2
    },
    {
      "productoId": "prod2",
      "cantidad": 1
    }
  ]
}
```
**Instrucciones:**
- En Postman, selecciona el método `POST`.
- Pega la URL.
- Ve a la pestaña "Body", selecciona "raw" y "JSON".
- Pega el JSON de ejemplo y haz clic en "Send".

---

### 2. Obtener el carrito de un usuario

**Método:** GET  
**URL:** `http://localhost:9090/api/carrito/usuario1`

**Instrucciones:**
- En Postman, selecciona el método `GET`.
- Pega la URL, reemplazando `usuario1` por el ID de usuario deseado.
- Haz clic en "Send".

---

### 3. Actualizar un carrito

**Método:** PUT  
**URL:** `http://localhost:9090/api/carrito/actualizar`  
**Body (JSON):**
```json
{
  "usuarioId": "usuario1",
  "items": [
    {
      "productoId": "prod1",
      "cantidad": 3
    }
  ]
}
```
**Instrucciones:**
- En Postman, selecciona el método `PUT`.
- Pega la URL.
- Ve a la pestaña "Body", selecciona "raw" y "JSON".
- Pega el JSON de ejemplo y haz clic en "Send".

---

### 4. Vaciar el carrito de un usuario

**Método:** DELETE  
**URL:** `http://localhost:9090/api/carrito/vaciar/usuario1`

**Instrucciones:**
- En Postman, selecciona el método `DELETE`.
- Pega la URL, reemplazando `usuario1` por el ID de usuario deseado.
- Haz clic en "Send".

---

## Notas

- Cambia `usuario1` y los IDs de producto por los datos que quieras testear.
- Si otros controladores como `ProductoController` o `UsuarioController` existen, pídeme específicamente que los revise para sumar sus endpoints.
- Asegúrate de que la base de datos MongoDB esté activa y accesible.

---

¿Te gustaría que agregue ejemplos para endpoints de productos o usuarios si existen?