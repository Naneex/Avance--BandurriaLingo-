package BandurriaLingo.backend.user;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BandurriaLingo.backend.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final JwtService jwtService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(HttpServletRequest request, @PathVariable Long id, @RequestBody Map<String, Object> updates) {
    String token = request.getHeader("Authorization");
    if (token == null || !token.startsWith("Bearer ")) {
        return ResponseEntity.status(403).body("Token no proporcionado o inválido");
    }

    token = token.substring(7);
    String usernameFromToken = jwtService.getUsuarioFromToken(token);

    // Obtener el usuario autenticado desde el token
    Usuario usuarioAutenticado = usuarioRepository.findByUsuario(usernameFromToken)
            .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));

    // Verificar si el usuario autenticado tiene permiso para actualizar este usuario
    if (!usuarioAutenticado.getId().equals(id)) {
        return ResponseEntity.status(403).body("No tienes permiso para modificar este usuario");
    }

    // Continuar con la actualización
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (updates.containsKey("exp")) {
        usuario.setExp((Integer) updates.get("exp"));
    }
    if (updates.containsKey("nivel")) {
        usuario.setNivel((Integer) updates.get("nivel"));
    }

    usuarioRepository.save(usuario);
    return ResponseEntity.ok("Usuario actualizado correctamente");
}
    
}
