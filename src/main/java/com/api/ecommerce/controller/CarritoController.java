package com.api.ecommerce.controller;

import com.api.ecommerce.model.Carrito;
import com.api.ecommerce.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoRepository carritoRepository;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoRepository.save(carrito));
    }

    @GetMapping("/{usuarioId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable String usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasRole('USER')")
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> vaciarCarrito(@PathVariable String usuarioId) {
        carritoRepository.deleteByUsuarioId(usuarioId);
        return ResponseEntity.ok("Carrito vaciado.");
    }
}
