package edu.dosw.parcial.controller;

import edu.dosw.parcial.core.models.ErrorResponse;
import edu.dosw.parcial.core.models.RegistroUsuarioRequest;
import edu.dosw.parcial.core.models.RegistroUsuarioResponse;
import edu.dosw.parcial.core.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios y autenticación")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario", description = "Registra un nuevo usuario en el sistema con su correo institucional y contraseña")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validación fallida - datos inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto - email ya registrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> registroUsuario(@RequestBody RegistroUsuarioRequest request) {
        try {
            RegistroUsuarioResponse response = usuarioService.registrarUsuario(request);
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
