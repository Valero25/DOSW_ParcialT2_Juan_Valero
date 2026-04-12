package edu.dosw.parcial.controller;

import edu.dosw.parcial.core.models.ErrorResponse;
import edu.dosw.parcial.core.models.ProductoRequest;
import edu.dosw.parcial.core.models.ProductoResponse;
import edu.dosw.parcial.core.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints para la gestión de productos con códigos QR")
public class ProductoController {
    
    private final ProductoService productoService;
    
    @PostMapping
    @Operation(summary = "Crear nuevo producto", description = "Crea un nuevo producto en el catálogo de la cafetería con su información y código QR")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validación fallida - datos inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
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
    @Operation(summary = "Obtener producto por ID", description = "Obtiene la información detallada de un producto específico mediante su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> obtenerProducto(
            @Parameter(description = "ID único del producto", required = true, example = "prod-001")
            @PathVariable String id) {
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
    @Operation(summary = "Listar todos los productos", description = "Obtiene el listado completo de productos disponibles en la cafetería")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class)))
    })
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        List<ProductoResponse> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }
}
