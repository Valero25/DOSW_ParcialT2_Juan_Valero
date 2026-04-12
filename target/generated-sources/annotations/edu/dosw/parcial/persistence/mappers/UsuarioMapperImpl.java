package edu.dosw.parcial.persistence.mappers;

import edu.dosw.parcial.core.models.RegistroUsuarioResponse;
import edu.dosw.parcial.persistence.entities.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-11T19:11:44-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public RegistroUsuarioResponse toResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        RegistroUsuarioResponse.RegistroUsuarioResponseBuilder registroUsuarioResponse = RegistroUsuarioResponse.builder();

        registroUsuarioResponse.email( usuario.getEmail() );
        registroUsuarioResponse.estado( usuario.getEstado() );
        registroUsuarioResponse.id( usuario.getId() );
        registroUsuarioResponse.nombre( usuario.getNombre() );
        registroUsuarioResponse.rol( usuario.getRol() );

        return registroUsuarioResponse.build();
    }
}
