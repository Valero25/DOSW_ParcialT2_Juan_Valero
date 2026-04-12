package edu.dosw.parcial.persistence.repositories;

import edu.dosw.parcial.persistence.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    Optional<Producto> findByNombre(String nombre);
}
