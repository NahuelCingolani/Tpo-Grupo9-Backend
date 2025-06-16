package com.api.ecommerce.controller;

import com.api.ecommerce.model.Producto;
import com.api.ecommerce.repository.ProductoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@PathVariable String id) {
        return productoRepository.findById(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productoRepository.deleteById(id);
    }

    @PostMapping("/importarMock")
public ResponseEntity<String> importarMock() {
    List<Producto> productos = List.of(
        new Producto(null,
            "Camiseta LeBron Lakers City Edition",
            "Camiseta oficial City Edition de LeBron James",
            75000.0,
            "https://www.redrat.co.nz/content/products/nike-x-nba-los-angeles-lakers-lebron-james-icon-swingman-jersey-youth-amarillo-side-detail-53964.jpg",
            List.of(
                "https://images.footballfanatics.com/los-angeles-lakers/los-angeles-lakers-nike-icon-swingman-jersey-james-23-mens_ss5_p-202685708",
                "https://fanatics.frgimages.com/los-angeles-lakers/mens-fanatics-lebron-james-gold-los-angeles-lakers-fast-break-replica-jersey-icon-edition.jpg"
            ),
            "Lakers",
            "LeBron James",
            "City Edition",
            Map.of("S", 1, "M", 4, "L", 2, "XL", 1)
        ),
        new Producto(null,
            "Camiseta Stephen Curry Warriors Home",
            "Versión titular blanca oficial de Stephen Curry",
            70000.0,
            "https://images.footballfanatics.com/stephen-curry-jersey.jpg",
            List.of(),
            "Warriors",
            "Stephen Curry",
            "Titular",
            Map.of("S", 2, "M", 3, "L", 1, "XL", 0)
        ),
        new Producto(null,
            "Camiseta Chicago Bulls Michael Jordan 1984",
            "Retro icónica de Jordan temporada 84",
            80000.0,
            "https://images.footballfanatics.com/jordan-1984-bulls.jpg",
            List.of(),
            "Bulls",
            "Michael Jordan",
            "Retro",
            Map.of("S", 1, "M", 2, "L", 1, "XL", 1)
        ),
        new Producto(null,
            "Camiseta Kevin Durant Phoenix Suns",
            "Versión alternativa púrpura de Durant",
            80000.0,
            "https://images.footballfanatics.com/durant-suns.jpg",
            List.of(),
            "Suns",
            "Kevin Durant",
            "Alternativa",
            Map.of("S", 2, "M", 4, "L", 3, "XL", 1)
        ),
        new Producto(null,
            "Camiseta Giannis Antetokounmpo Milwaukee Bucks",
            "Camiseta verde oficial del griego",
            90000.0,
            "https://images.footballfanatics.com/giannis-bucks.jpg",
            List.of(),
            "Bucks",
            "Giannis Antetokounmpo",
            "Titular",
            Map.of("S", 1, "M", 3, "L", 2, "XL", 2)
        ),
        new Producto(null,
            "Camiseta Tyrese Haliburton Indiana Pacers",
            "Versión azul marino Haliburton Pacers",
            95000.0,
            "https://images.footballfanatics.com/haliburton-pacers.jpg",
            List.of(),
            "Pacers",
            "Tyrese Haliburton",
            "Titular",
            Map.of("S", 1, "M", 3, "L", 3, "XL", 1)
        ),
        new Producto(null,
            "Camiseta Luka Dončić Lakers",
            "Edición especial amarilla de Luka",
            80000.0,
            "https://images.footballfanatics.com/luka-lakers.jpg",
            List.of(),
            "Lakers",
            "Luka Dončić",
            "City Edition",
            Map.of("S", 1, "M", 4, "L", 4, "XL", 1)
        ),
        new Producto(null,
            "Camiseta Joel Embiid Philadelphia 76ers",
            "Camiseta azul oficial de Embiid",
            85000.0,
            "https://images.footballfanatics.com/embiid-76ers.jpg",
            List.of(),
            "76ers",
            "Joel Embiid",
            "Titular",
            Map.of("S", 2, "M", 2, "L", 2, "XL", 2)
        )
    );
    int nuevos = 0;
    for (Producto p : productos) {
        boolean yaExiste = productoRepository
            .findByNombreAndEquipoAndJugador(p.getNombre(), p.getEquipo(), p.getJugador())
            .isPresent();

        if (!yaExiste) {
            productoRepository.save(p);
            nuevos++;
        }
    }

    return ResponseEntity.ok(nuevos + " productos nuevos agregados.");
}
@DeleteMapping("/eliminarTodos")
public ResponseEntity<String> eliminarTodosLosProductos() {
    productoRepository.deleteAll();
    return ResponseEntity.ok("Todos los productos fueron eliminados.");
}
}

