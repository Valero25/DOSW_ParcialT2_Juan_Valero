package edu.dosw.parcial.persistence.repositories;

import edu.dosw.parcial.persistence.entities.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, String> {
    List<PedidoDetalle> findByPedidoId(String pedidoId);
}
