# Pruebas técnicas con Postman y posibles mejoras

Este documento explica cómo probar los endpoints del backend con Postman, qué esperar de cada uno, y sugiere mejoras a nivel de código para cada archivo clave.

---

## 1. **Pruebas con Postman**

### 1.1 Registro de usuario

- **URL:** `POST http://localhost:8080/api/auth/register`
- **Body (JSON):**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@mail.com",
  "password": "1234",
  "role": "USER" // O "ADMIN"
}
```
- **Respuesta esperada:**  
  Código 200 OK, y un objeto con el campo `token` (JWT). 

---

### 1.2 Login de usuario

- **URL:** `POST http://localhost:8080/api/auth/login`
- **Body (JSON):**
```json
{
  "email": "juan@mail.com",
  "password": "1234"
}
```
- **Respuesta esperada:**  
  Código 200 OK, y un objeto con el campo `token`.

---

### 1.3 Crear carrito

- **URL:** `POST http://localhost:9090/api/carrito`
- **Headers:**  
  `Authorization: Bearer <token>` (el token obtenido en login/registro)
- **Body (JSON):**
```json
{
  "usuarioId": "<id_usuario>", // O el email, dependiendo implementación real
  "items": [
    {"productoId": "<id_producto>", "cantidad": 2, "talle": "M"}
  ]
}
```
- **Respuesta esperada:**  
  Carrito creado o error de stock/producto.

---

### 1.4 Consultar carrito

- **URL:** `GET http://localhost:9090/api/carrito/<usuarioId>`
- **Headers:**  
  `Authorization: Bearer <token>`
- **Respuesta esperada:**  
  Carrito del usuario o 404 si no existe.

---

### 1.5 Actualizar carrito

- **URL:** `PUT http://localhost:9090/api/carrito/actualizar`
- **Headers:**  
  `Authorization: Bearer <token>`
- **Body (JSON):**
```json
{
  "usuarioId": "<id_usuario>",
  "items": [
    {"productoId": "<id_producto>", "cantidad": 1, "talle": "M"}
  ]
}
```
- **Respuesta esperada:**  
  Mensaje de éxito o error de stock.

---

### 1.6 Vaciar carrito

- **URL:** `DELETE http://localhost:9090/api/carrito/vaciar/<usuarioId>`
- **Headers:**  
  `Authorization: Bearer <token>`
- **Respuesta esperada:**  
  Mensaje de éxito.

---

## 2. **Consideraciones importantes**

- El JWT **debe** enviarse en el header Authorization para los endpoints protegidos.
- El usuario debe tener el rol adecuado (`USER` para el carrito, etc).
- Los errores de stock, producto no encontrado o usuario no encontrado se manejan con mensajes claros y status HTTP apropiados.
- El registro de usuarios permite elegir el rol, pero en un entorno real esto debería ser solo para admins.

---

## 3. **Mejoras sugeridas (por archivo)**

### **ApplicationConfig.java**
- [ ] Extraer la lógica de `UserDetailsService` a una clase aparte (ya existe UsuarioDetailsService, podría usarse).
- [ ] Soportar más estrategias de autenticación si se requiere.

### **SecurityConfig.java**
- [ ] Permitir que algunos endpoints de productos sean públicos para listar, pero restringir modificación a admin.
- [ ] Agregar CORS configurado de forma más segura si va a producción.

### **AuthController.java**
- [ ] Validar formato de email y fuerza de password.
- [ ] Devolver errores más detallados en caso de login fallido (ahora solo lanza excepción).

### **CarritoController.java**
- [ ] Validar que el usuario autenticado sea quien manipula su propio carrito (no solo por usuarioId).
- [ ] Permitir agregar/eliminar un solo producto del carrito como endpoints separados.
- [ ] Mejorar los mensajes de error de stock.

### **JwtAuthenticationFilter.java / JwtService.java**
- [ ] Usar una clave secreta externa (no hardcodeada).
- [ ] Permitir revocación de tokens si el usuario cierra sesión.
- [ ] Mejor manejo de expiración y renovación de token.

### **Modelos (Usuario, Carrito, Producto)**
- [ ] Agregar validaciones (por ejemplo, no permitir precios negativos, emails únicos).
- [ ] Documentar los campos con Swagger o similar.

### **Repositories**
- [ ] Agregar índices en MongoDB para búsquedas más rápidas.
- [ ] Agregar más métodos útiles según crezcan las necesidades.

### **GlobalExceptionHandler.java**
- [ ] Personalizar más los mensajes de error (actualmente algunos devuelven "hola :)").
- [ ] Devolver un objeto JSON con código y mensaje, no solo string.

### **General**
- [ ] Agregar tests automáticos de endpoints.
- [ ] Documentar la API con Swagger/OpenAPI.
- [ ] Implementar endpoints de productos y usuarios completos.

---

## 4. **Resumen**

- Prueba primero el registro/login para obtener el token.
- Usa el token para las operaciones del carrito.
- Maneja los errores según los mensajes devueltos.
- Considera las mejoras para robustecer y profesionalizar la API.

¿Quieres una colección de Postman lista para importar o ejemplos de pruebas automáticas en Java?