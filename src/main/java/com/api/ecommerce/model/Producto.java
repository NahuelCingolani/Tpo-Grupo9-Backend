package com.api.ecommerce.model;
import java.util.Map;

import lombok.*;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    private String id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagenUrl;
    private List<String> imagenes;
    private String equipo;
    private String jugador;
    private String categoria;
    private Map<String, Integer> stockPorTalle;
}
