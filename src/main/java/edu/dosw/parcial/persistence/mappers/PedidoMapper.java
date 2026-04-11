package edu.dosw.parcial.persistence.mappers;

import edu.dosw.parcial.core.models.CrearPedidoResponse;
import edu.dosw.parcial.persistence.entities.Pedido;
import edu.dosw.parcial.persistence.entities.PedidoDetalle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    CrearPedidoResponse.ItemPedidoResponse toItemResponse(PedidoDetalle detalle);
}
