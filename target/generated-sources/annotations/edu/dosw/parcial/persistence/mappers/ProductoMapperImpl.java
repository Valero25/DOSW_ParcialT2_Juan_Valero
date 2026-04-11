package edu.dosw.parcial.persistence.mappers;

import edu.dosw.parcial.core.models.ProductoResponse;
import edu.dosw.parcial.persistence.entities.Producto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-11T17:27:28-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoResponse toResponse(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoResponse.ProductoResponseBuilder productoResponse = ProductoResponse.builder();

        productoResponse.descripcion( producto.getDescripcion() );
        productoResponse.id( producto.getId() );
        productoResponse.nombre( producto.getNombre() );
        productoResponse.precio( producto.getPrecio() );
        productoResponse.stock( producto.getStock() );

        return productoResponse.build();
    }
}
