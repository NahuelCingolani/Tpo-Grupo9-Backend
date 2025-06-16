package com.api.ecommerce.controller;

import com.api.ecommerce.model.Carrito;
import com.api.ecommerce.model.ItemCarrito;
import com.api.ecommerce.repository.CarritoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoRepository carritoRepository;

    public CarritoController(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable String usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAlCarrito(@RequestBody Carrito nuevoItem) {
        Optional<Carrito> existente = carritoRepository.findByUsuarioId(nuevoItem.getUsuarioId());

        if (existente.isPresent()) {
            Carrito carrito = existente.get();
            carrito.getItems().addAll(nuevoItem.getItems());
            carritoRepository.save(carrito);
        } else {
            carritoRepository.save(nuevoItem);
        }

        return ResponseEntity.ok("Producto agregado al carrito.");
    }
    @PostMapping
public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
    Carrito nuevo = carritoRepository.save(carrito);
    return ResponseEntity.ok(nuevo);
}


    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCarrito(@RequestBody Carrito actualizado) {
        Optional<Carrito> existente = carritoRepository.findByUsuarioId(actualizado.getUsuarioId());

        if (existente.isPresent()) {
            Carrito carrito = existente.get();
            carrito.setItems(actualizado.getItems());
            carritoRepository.save(carrito);
            return ResponseEntity.ok("Carrito actualizado.");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public ResponseEntity<String> vaciarCarrito(@PathVariable String usuarioId) {
        carritoRepository.deleteByUsuarioId(usuarioId);
        return ResponseEntity.ok("Carrito vaciado.");
    }
}
