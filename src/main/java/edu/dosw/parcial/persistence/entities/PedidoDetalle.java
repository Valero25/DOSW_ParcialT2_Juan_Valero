package edu.dosw.parcial.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pedidos_detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDetalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String pedidoId;
    
    @Column(nullable = false)
    private String productoId;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal precio;
}
