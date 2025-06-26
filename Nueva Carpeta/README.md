# Tpo-Grupo9-Backend

Este proyecto es un backend desarrollado en Java usando Spring Boot, Maven y MongoDB para la gestión de un sistema ecommerce.

---

## Requisitos previos

- **Java JDK 17** o superior  
- **Maven**  
- **MongoDB** (local o en la nube)  
- (Opcional) **Git** para clonar el repositorio

---

## 1. Clonar el repositorio

```sh
git clone https://github.com/NahuelCingolani/Tpo-Grupo9-Backend.git
cd Tpo-Grupo9-Backend/ecommerce
```

---

## 2. Configurar la base de datos MongoDB

Por defecto, la app buscará una instancia de MongoDB corriendo en tu máquina local en el puerto `27017` y usará la base de datos `ecommerce`.

Esto está definido en `src/main/resources/application.properties`:
```ini
spring.data.mongodb.uri=mongodb://localhost:27017/ecommerce
spring.application.name=ecommerce
server.port=9090
```

### Si quieres modificar la conexión (usuario, pass o host), edita el archivo `application.properties`:

```ini
spring.data.mongodb.uri=mongodb://<usuario>:<contraseña>@<host>:<puerto>/<nombre_db>
```

---

## 3. Levantar MongoDB

- **Local:**  
  Si tienes MongoDB instalado localmente, inicia el servicio.
- **Con Docker:**  
  Puedes levantar una base de datos MongoDB con Docker:
  ```sh
  docker run -d --name mongo -p 27017:27017 mongo:latest
  ```

---

## 4. Compilar y correr el proyecto

Asegúrate de estar en la carpeta `ecommerce` (donde está el archivo `pom.xml`).

### Opción A: Usando Maven Wrapper (recomendado si no tienes Maven global)

```sh
./mvnw clean install
./mvnw spring-boot:run
```

### Opción B: Usando Maven global

```sh
mvn clean install
mvn spring-boot:run
```

- El backend se levantará en:  
  ```
  http://localhost:9090
  ```

---

## 5. Probar la API

Puedes acceder a los endpoints REST usando herramientas como Postman, Insomnia, o simplemente cURL. Por ejemplo:

- Para ver el carrito de un usuario (GET):
  ```
  GET http://localhost:9090/api/carrito/{usuarioId}
  ```
- Para crear un carrito (POST):
  ```
  POST http://localhost:9090/api/carrito
  Content-Type: application/json

  {
    "usuarioId": "usuario123",
    "items": [
      {
        "productoId": "prod1",
        "cantidad": 2
      }
    ]
  }
  ```

---

## 6. Personalizaciones y configuración avanzada

- Puedes cambiar el puerto modificando `server.port` en `application.properties`.
- Si necesitas agregar variables de entorno, Spring Boot las reconoce automáticamente.
- Para agregar dependencias o modificar la build, edita el archivo `pom.xml`.

---

## 7. Pruebas

Para correr los tests automáticos:
```sh
mvn test
```

---

## 8. Preguntas frecuentes

- **¿No puedo conectar a MongoDB?**  
  Asegúrate de que el servicio está corriendo y que los datos de conexión en `application.properties` son correctos.

- **¿Cómo cambio la base de datos o el puerto?**  
  Modifica los valores en `application.properties`.

---

## 9. Estructura del Proyecto

```
Tpo-Grupo9-Backend/
└── ecommerce/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   └── resources/
    │   └── test/
    ├── pom.xml
    └── ...
```

---

## 10. Ayuda adicional

- [Documentación oficial de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Maven](https://maven.apache.org/guides/index.html)
- [Documentación de MongoDB](https://docs.mongodb.com/manual/)

---

¡Listo! Siguiendo estos pasos deberías poder levantar el backend sin problemas.