package edu.dosw.parcial.core.services;

import edu.dosw.parcial.core.models.RegistroUsuarioRequest;
import edu.dosw.parcial.core.models.RegistroUsuarioResponse;
import edu.dosw.parcial.core.utils.RolUsuario;
import edu.dosw.parcial.core.validators.UsuarioValidator;
import edu.dosw.parcial.persistence.entities.Usuario;
import edu.dosw.parcial.persistence.mappers.UsuarioMapper;
import edu.dosw.parcial.persistence.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidator usuarioValidator;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    
    public RegistroUsuarioResponse registrarUsuario(RegistroUsuarioRequest request) {
        
        List<String> errores = usuarioValidator.validarRegistro(request);
        if (!errores.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errores));
        }
        
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(request.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .nombre(request.getNombre())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(RolUsuario.CLIENTE.name())
                .estado("ACTIVO")
                .build();
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        
        return usuarioMapper.toResponse(usuarioGuardado);
    }
    
    public Usuario obtenerUsuarioPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
    
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
