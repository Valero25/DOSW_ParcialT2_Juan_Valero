package edu.dosw.parcial.core.validators;

import edu.dosw.parcial.core.models.CrearPedidoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoValidator {
    
    public List<String> validarCreacion(CrearPedidoRequest request) {
        List<String> errores = new ArrayList<>();
        
        if (request == null) {
            errores.add("La solicitud no puede ser nula");
            return errores;
        }
        
        if (request.getUsuarioId() == null || request.getUsuarioId().trim().isEmpty()) {
            errores.add("El ID del usuario es requerido");
        }
        
        if (request.getItems() == null || request.getItems().isEmpty()) {
            errores.add("El pedido debe contener al menos 1 producto");
        } else {
            for (int i = 0; i < request.getItems().size(); i++) {
                CrearPedidoRequest.ItemPedidoRequest item = request.getItems().get(i);
                
                if (item.getProductoId() == null || item.getProductoId().trim().isEmpty()) {
                    errores.add("El ID del producto [" + i + "] es requerido");
                }
                
                if (item.getCantidad() == null || item.getCantidad() <= 0) {
                    errores.add("La cantidad del producto [" + i + "] debe ser mayor a 0");
                }
            }
        }
        
        return errores;
    }
}
