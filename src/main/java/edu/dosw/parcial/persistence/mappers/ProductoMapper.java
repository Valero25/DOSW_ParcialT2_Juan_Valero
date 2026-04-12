package edu.dosw.parcial.persistence.mappers;

import edu.dosw.parcial.core.models.ProductoResponse;
import edu.dosw.parcial.persistence.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoResponse toResponse(Producto producto);
}
