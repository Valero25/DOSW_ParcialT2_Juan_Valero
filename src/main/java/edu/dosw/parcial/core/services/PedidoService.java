package edu.dosw.parcial.core.services;

import edu.dosw.parcial.core.models.CrearPedidoRequest;
import edu.dosw.parcial.core.models.CrearPedidoResponse;
import edu.dosw.parcial.core.utils.EstadoPedido;
import edu.dosw.parcial.core.validators.PedidoValidator;
import edu.dosw.parcial.persistence.entities.Pedido;
import edu.dosw.parcial.persistence.entities.PedidoDetalle;
import edu.dosw.parcial.persistence.entities.Producto;
import edu.dosw.parcial.persistence.mappers.PedidoMapper;
import edu.dosw.parcial.persistence.repositories.PedidoDetalleRepository;
import edu.dosw.parcial.persistence.repositories.PedidoRepository;
import edu.dosw.parcial.persistence.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final PedidoDetalleRepository pedidoDetalleRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoService productoService;
    private final PedidoValidator pedidoValidator;
    private final PedidoMapper pedidoMapper;
    
    @Transactional
    public CrearPedidoResponse crearPedido(CrearPedidoRequest request) {
        
        List<String> errores = pedidoValidator.validarCreacion(request);
        if (!errores.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errores));
        }
        
        if (!usuarioRepository.existsById(request.getUsuarioId())) {
            throw new IllegalArgumentException("El usuario especificado no existe");
        }
        
        Pedido pedido = Pedido.builder()
                .usuarioId(request.getUsuarioId())
                .estado(EstadoPedido.CREADO.name())
                .total(BigDecimal.ZERO)
                .build();
        
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        
        BigDecimal total = BigDecimal.ZERO;
        List<PedidoDetalle> detalles = new java.util.ArrayList<>();
        
        for (CrearPedidoRequest.ItemPedidoRequest item : request.getItems()) {
            Producto producto = productoService.obtenerProductoEntidad(item.getProductoId());
            
            if (producto.getStock() < item.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
            }
            
            BigDecimal subtotal = producto.getPrecio().multiply(new BigDecimal(item.getCantidad()));
            
            PedidoDetalle detalle = PedidoDetalle.builder()
                    .pedidoId(pedidoGuardado.getId())
                    .productoId(item.getProductoId())
                    .cantidad(item.getCantidad())
                    .precio(producto.getPrecio())
                    .build();
            
            detalles.add(pedidoDetalleRepository.save(detalle));
            total = total.add(subtotal);
        }
        
        pedidoGuardado.setTotal(total);
        pedidoGuardado.setFechaActualizacion(LocalDateTime.now());
        pedidoRepository.save(pedidoGuardado);
        
        return CrearPedidoResponse.builder()
                .id(pedidoGuardado.getId())
                .usuarioId(pedidoGuardado.getUsuarioId())
                .estado(pedidoGuardado.getEstado())
                .total(pedidoGuardado.getTotal())
                .fechaCreacion(pedidoGuardado.getFechaCreacion())
                .items(detalles.stream()
                        .map(pedidoMapper::toItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }
    
    public Pedido obtenerPedidoPorId(String id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
    }
    
    public List<Pedido> obtenerPedidosPorUsuario(String usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
