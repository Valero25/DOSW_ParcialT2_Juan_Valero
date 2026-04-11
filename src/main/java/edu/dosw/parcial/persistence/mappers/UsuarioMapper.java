package edu.dosw.parcial.persistence.mappers;

import edu.dosw.parcial.core.models.RegistroUsuarioRequest;
import edu.dosw.parcial.core.models.RegistroUsuarioResponse;
import edu.dosw.parcial.persistence.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    RegistroUsuarioResponse toResponse(Usuario usuario);
}
