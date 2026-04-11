package edu.dosw.parcial.controller;

import edu.dosw.parcial.core.models.ErrorResponse;
import edu.dosw.parcial.core.models.ProductoRequest;
import edu.dosw.parcial.core.models.ProductoResponse;
import edu.dosw.parcial.core.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    
    private final ProductoService productoService;
    
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody ProductoRequest request) {
        try {
            ProductoResponse response = productoService.crearProducto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = ErrorResponse.builder()
                    .codigo(400)
                    .mensaje(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            ErrorResponse error = ErrorResponse.builder()
                    .codigo(500)
                    .mensaje("Error interno del servidor")
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable String id) {
        try {
            ProductoResponse response = productoService.obtenerProducto(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = ErrorResponse.builder()
                    .codigo(404)
                    .mensaje(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        List<ProductoResponse> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }
}
