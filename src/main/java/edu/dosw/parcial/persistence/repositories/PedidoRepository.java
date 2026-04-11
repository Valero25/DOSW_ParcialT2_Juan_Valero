package edu.dosw.parcial.persistence.repositories;

import edu.dosw.parcial.persistence.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {
    List<Pedido> findByUsuarioId(String usuarioId);
}
