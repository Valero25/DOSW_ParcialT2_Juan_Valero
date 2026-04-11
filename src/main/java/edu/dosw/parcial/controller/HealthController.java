package edu.dosw.parcial.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
@Tag(name = "Health", description = "Endpoints básicos de verificación de disponibilidad de la API")
public class HealthController extends BaseController {

    @GetMapping
    @Operation(summary = "Verifica el estado de la API", description = "Retorna un payload mínimo para confirmar que la aplicación está arriba.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "API disponible")
    })
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("service", "ECIXPRESS API");
        response.put("timestamp", OffsetDateTime.now().toString());
        return ResponseEntity.ok(response);
    }
}

