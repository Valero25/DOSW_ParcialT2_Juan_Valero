package edu.dosw.parcial.controller;

import edu.dosw.parcial.core.models.ErrorResponse;
import edu.dosw.parcial.core.models.RegistroUsuarioRequest;
import edu.dosw.parcial.core.models.RegistroUsuarioResponse;
import edu.dosw.parcial.core.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping("/registro")
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
