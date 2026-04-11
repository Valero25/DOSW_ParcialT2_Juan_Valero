package edu.dosw.parcial.core.services;

import edu.dosw.parcial.core.models.ProductoRequest;
import edu.dosw.parcial.core.models.ProductoResponse;
import edu.dosw.parcial.core.validators.ProductoValidator;
import edu.dosw.parcial.persistence.entities.Producto;
import edu.dosw.parcial.persistence.mappers.ProductoMapper;
import edu.dosw.parcial.persistence.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    private final ProductoValidator productoValidator;
    private final ProductoMapper productoMapper;
    
    public ProductoResponse crearProducto(ProductoRequest request) {
        
        List<String> errores = productoValidator.validarCreacion(request);
        if (!errores.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errores));
        }
        
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .build();
        
        Producto productoGuardado = productoRepository.save(producto);
        
        return productoMapper.toResponse(productoGuardado);
    }
    
    public ProductoResponse obtenerProducto(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return productoMapper.toResponse(producto);
    }
    
    public Producto obtenerProductoEntidad(String id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }
    
    public List<ProductoResponse> listarProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
