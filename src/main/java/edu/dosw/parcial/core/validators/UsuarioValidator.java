package edu.dosw.parcial.core.validators;

import edu.dosw.parcial.core.models.RegistroUsuarioRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UsuarioValidator {
    
    private static final String EMAIL_PATTERN = 
            "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    
    public List<String> validarRegistro(RegistroUsuarioRequest request) {
        List<String> errores = new ArrayList<>();
        
        if (request == null) {
            errores.add("La solicitud no puede ser nula");
            return errores;
        }
        
        if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
            errores.add("El nombre es requerido");
        } else if (request.getNombre().length() < 3) {
            errores.add("El nombre debe tener al menos 3 caracteres");
        }
        
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            errores.add("El email es requerido");
        } else if (!validarEmail(request.getEmail())) {
            errores.add("El formato del email no es válido");
        }
        
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            errores.add("La contraseña es requerida");
        } else if (request.getPassword().length() < 8) {
            errores.add("La contraseña debe tener al menos 8 caracteres");
        }
        
        return errores;
    }
    
    private boolean validarEmail(String email) {
        return emailPattern.matcher(email).matches();
    }
}
