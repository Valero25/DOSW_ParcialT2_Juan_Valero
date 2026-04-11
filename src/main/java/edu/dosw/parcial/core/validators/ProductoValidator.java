package edu.dosw.parcial.core.validators;

import edu.dosw.parcial.core.models.ProductoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductoValidator {
    
    public List<String> validarCreacion(ProductoRequest request) {
        List<String> errores = new ArrayList<>();
        
        if (request == null) {
            errores.add("La solicitud no puede ser nula");
            return errores;
        }
        
        if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
            errores.add("El nombre del producto es requerido");
        } else if (request.getNombre().length() < 3) {
            errores.add("El nombre del producto debe tener al menos 3 caracteres");
        }
        
        if (request.getPrecio() == null || request.getPrecio().signum() <= 0) {
            errores.add("El precio debe ser mayor a 0");
        }
        
        if (request.getStock() == null || request.getStock() < 0) {
            errores.add("El stock no puede ser negativo");
        }
        
        return errores;
    }
}
