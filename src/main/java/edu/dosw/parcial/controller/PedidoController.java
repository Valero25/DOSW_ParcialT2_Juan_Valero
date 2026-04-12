package edu.dosw.parcial.controller;

import edu.dosw.parcial.core.models.CrearPedidoRequest;
import edu.dosw.parcial.core.models.CrearPedidoResponse;
import edu.dosw.parcial.core.models.ErrorResponse;
import edu.dosw.parcial.core.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Endpoints para la gestión de pedidos en cafeterías")
public class PedidoController {
    
    private final PedidoService pedidoService;
    
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Crear nuevo pedido", description = "Crea un nuevo pedido con los productos y cantidades especificadas. El usuario solo puede tener un pedido activo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearPedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validación fallida - datos inválidos o stock insuficiente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado - token requerido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> crearPedido(@RequestBody CrearPedidoRequest request) {
        try {
            CrearPedidoResponse response = pedidoService.crearPedido(request);
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
}
