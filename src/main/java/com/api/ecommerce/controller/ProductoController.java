package com.api.ecommerce.controller;
 
import com.api.ecommerce.model.Producto;
import com.api.ecommerce.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.*;
 
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
 
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String id, @RequestBody Producto productoNuevo) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productoNuevo.setId(id);
        return ResponseEntity.ok(productoRepository.save(productoNuevo));
    }
 
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialProducto(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
 
        Producto producto = optionalProducto.get();
 
        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> producto.setNombre((String) value);
                case "descripcion" -> producto.setDescripcion((String) value);
                case "precio" -> producto.setPrecio(Double.valueOf(value.toString()));
                case "imagenUrl" -> producto.setImagenUrl((String) value);
                case "imagenes" -> {
                    List<?> rawList = (List<?>) value;
                    List<String> imagenes = new ArrayList<>();
                    for (Object obj : rawList) {
                        imagenes.add(obj.toString());
                    }
                    producto.setImagenes(imagenes);
                }
                case "equipo" -> producto.setEquipo((String) value);
                case "jugador" -> producto.setJugador((String) value);
                case "categoria" -> producto.setCategoria((String) value);
                case "stockPorTalle" -> {
                    Map<?, ?> rawMap = (Map<?, ?>) value;
                    Map<String, Integer> stockMap = new HashMap<>();
                    rawMap.forEach((k, v) -> stockMap.put(k.toString(), Integer.valueOf(v.toString())));
                    producto.setStockPorTalle(stockMap);
                }
            }
        });
 
        return ResponseEntity.ok(productoRepository.save(producto));
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
                "Lakers", "LeBron James", "City Edition",
                Map.of("S", 1, "M", 4, "L", 2, "XL", 1)
            )
            // Agregá más si querés
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
    @GetMapping("/equipo/{equipo}")
    public ResponseEntity<List<Producto>> obtenerPorEquipo(@PathVariable String equipo) {
        List<Producto> productos = productoRepository.findByEquipoIgnoreCase(equipo);
        return ResponseEntity.ok(productos);
    }
}