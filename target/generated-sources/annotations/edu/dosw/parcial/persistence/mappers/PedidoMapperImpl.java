package edu.dosw.parcial.persistence.mappers;

import edu.dosw.parcial.core.models.CrearPedidoResponse;
import edu.dosw.parcial.persistence.entities.PedidoDetalle;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-11T17:25:57-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public CrearPedidoResponse.ItemPedidoResponse toItemResponse(PedidoDetalle detalle) {
        if ( detalle == null ) {
            return null;
        }

        CrearPedidoResponse.ItemPedidoResponse.ItemPedidoResponseBuilder itemPedidoResponse = CrearPedidoResponse.ItemPedidoResponse.builder();

        itemPedidoResponse.cantidad( detalle.getCantidad() );
        itemPedidoResponse.precio( detalle.getPrecio() );
        itemPedidoResponse.productoId( detalle.getProductoId() );

        return itemPedidoResponse.build();
    }
}
