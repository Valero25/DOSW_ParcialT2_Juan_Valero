package edu.dosw.parcial.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroUsuarioResponse {
    private String id;
    private String nombre;
    private String email;
    private String rol;
    private String estado;
}
