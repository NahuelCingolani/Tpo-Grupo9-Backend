# ¿Cómo funciona este backend? (Explicación simple)

Este backend es una API para un e-commerce, hecha en Java con Spring Boot y MongoDB. Permite que los usuarios se registren, inicien sesión y gestionen un carrito de compras. También implementa seguridad con JWT (tokens) y roles (admin y usuario). Aquí va su funcionamiento explicado con palabras simples:

---

## 1. **Usuarios, roles y autenticación**

- **Registro e inicio de sesión:**  
  El usuario se puede registrar con nombre, email, contraseña y rol (admin o usuario). Luego puede iniciar sesión con email y contraseña.
- **Seguridad:**  
  Si el login es exitoso, el backend responde con un **token JWT**. Este token se debe usar en futuras peticiones para acceder a rutas protegidas.
- **Roles:**  
  Hay dos roles: **USER** (usuario común) y **ADMIN**.  
  Los endpoints de productos son solo para admin, el carrito es solo para usuarios comunes.

---

## 2. **¿Cómo se guarda la información?**

- **MongoDB** almacena los datos.
- Hay colecciones para **usuarios**, **carritos** y **productos**.
- El modelo **Carrito** tiene una lista de productos y cantidades por usuario.

---

## 3. **¿Qué hace cada archivo importante?**

### Seguridad y Configuración
- **ApplicationConfig.java**  
  Configura cómo buscar usuarios por email y cómo se codifican las contraseñas.
- **SecurityConfig.java**  
  Define qué rutas son públicas y cuáles requieren login/rol. Usa el filtro de JWT para validar tokens.
- **JwtService.java** y **JwtAuthenticationFilter.java**  
  Se encargan de crear y validar los tokens JWT. El filtro intercepta pedidos HTTP y verifica el token.

### Usuarios y Autenticación
- **Usuario.java**  
  Define el modelo de usuario, con su rol y datos personales.  
- **Role.java**  
  Enumera los roles: USER y ADMIN.
- **UsuarioRepository.java**  
  Permite buscar usuarios en la base de datos.
- **AuthService.java**  
  Lógica para registrarse e iniciar sesión.
- **AuthController.java**  
  Expone los endpoints `/api/auth/register` y `/api/auth/login`.

### Carrito
- **Carrito.java**, **ItemCarrito.java**  
  Modelan el carrito y sus productos.
- **CarritoRepository.java**  
  Permite buscar y modificar carritos en la base de datos.
- **CarritoController.java**  
  Endpoints para crear, ver, actualizar y vaciar el carrito. Valida stock antes de agregar productos.

### Productos
- **Producto.java**, **ProductoRepository.java**  
  Modelan los productos y su persistencia. (No se expone controlador, pero sí es usado por el carrito).

### Excepciones y manejo de errores
- **GlobalExceptionHandler.java**  
  Responde con mensajes claros cuando algo falla (por ejemplo, usuario no encontrado o stock insuficiente).
- **MailNotFoundException.java**, **PrecioNegativoException.java**, etc.  
  Definen errores personalizados para distintos problemas.

---

## 4. **¿Cómo se relacionan?**

- Cuando un usuario se registra o loguea, se guarda/busca en la base y se genera un token.
- El token se usa para acceder a rutas protegidas (como el carrito).  
  El filtro de seguridad verifica ese token en cada pedido.
- Si un usuario intenta modificar su carrito, primero se verifica que tenga stock suficiente.  
  Si hay algún error (usuario/producto no encontrado, stock insuficiente, etc.), se responde con un mensaje adecuado.

---

## Resumiendo:

- **Usuarios** usan la API para registrarse, loguearse y gestionar su carrito.
- **Admins** pueden (en el futuro) gestionar productos.
- **Todo acceso está protegido** por roles y tokens.
- **MongoDB** guarda toda la información.
