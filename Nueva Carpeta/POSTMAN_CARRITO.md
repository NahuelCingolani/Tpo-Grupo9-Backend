# Pruebas del API de Carrito con Postman

Este servicio implementa un carrito de compras usando endpoints REST bajo `/api/carrito`. Aquí tienes ejemplos para probar cada endpoint con Postman.

---

## 1. Crear un carrito

**Método:** POST  
**URL:** `http://localhost:9090/api/carrito`  
**Body (JSON, raw):**
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
**Pasos en Postman:**
- Selecciona `POST` y pega la URL.
- Ve a "Body" → "raw" → selecciona "JSON".
- Pega el JSON y envía la solicitud.

---

## 2. Obtener el carrito de un usuario

**Método:** GET  
**URL:**  
```
http://localhost:9090/api/carrito/usuario1
```
**Pasos en Postman:**
- Selecciona `GET` y pega la URL (cambia usuario1 por el usuario deseado).
- Envía la solicitud.

---

## 3. Actualizar el carrito de un usuario

**Método:** PUT  
**URL:** `http://localhost:9090/api/carrito/actualizar`  
**Body (JSON, raw):**
```json
{
  "usuarioId": "usuario1",
  "items": [
    {
      "productoId": "prod1",
      "cantidad": 5
    }
  ]
}
```
**Pasos en Postman:**
- Selecciona `PUT` y pega la URL.
- Ve a "Body" → "raw" → "JSON".
- Pega el JSON y envía.

---

## 4. Vaciar el carrito de un usuario

**Método:** DELETE  
**URL:**  
```
http://localhost:9090/api/carrito/vaciar/usuario1
```
**Pasos en Postman:**
- Selecciona `DELETE` y pega la URL (cambia usuario1 por el usuario deseado).
- Envía la solicitud.

---

## Notas adicionales

- Los IDs de producto y usuario pueden ser cualquier string.
- La estructura de los items corresponde a la clase `ItemCarrito` (tiene `productoId` y `cantidad`).
- El backend guarda la información en MongoDB (verifica que esté en funcionamiento).

---

¿Quieres una colección de Postman lista para importar? ¿O necesitas pruebas automáticas para otros endpoints si se agregan más dominios?