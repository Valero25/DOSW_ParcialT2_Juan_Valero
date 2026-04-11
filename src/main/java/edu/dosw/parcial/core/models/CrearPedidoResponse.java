package edu.dosw.parcial.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearPedidoResponse {
    private String id;
    private String usuarioId;
    private String estado;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private List<ItemPedidoResponse> items;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemPedidoResponse {
        private String productoId;
        private Integer cantidad;
        private BigDecimal precio;
    }
}
