# 🛒 TPO Grupo 9 - Backend de Ecommerce
Este repositorio contiene el backend del proyecto de ecommerce realizado por el Grupo 9. 
Fue desarrollado con **Spring Boot**, conectado a **MongoDB** y configurado para funcionar con **Docker**.

## 🔧 Pasos para levantar el proyecto

### 1. Clonar el repositorio
git clone https://github.com/NahuelCingolani/Tpo-Grupo9-Backend.git
cd Tpo-Grupo9-Backend
git checkout backend

### 2. Requisitos previos 
tener Docker instalado y corriendo 
Tener Java 17 y Maven 

### 3. Levantar el proyecto con Docker
docker compose up --build

### 4. Probar en Postman 
GET http://localhost:8080/api/productos → lista de productos

POST http://localhost:8080/api/productos/importarMock → carga productos mock

DELETE http://localhost:8080/api/productos/eliminarTodos → elimina todos los productos
