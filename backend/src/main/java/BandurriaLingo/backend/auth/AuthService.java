
package BandurriaLingo.backend.auth;

import org.springframework.stereotype.Service;

import BandurriaLingo.backend.jwt.JwtService;
import BandurriaLingo.backend.user.Role;
import BandurriaLingo.backend.user.Usuario;
import BandurriaLingo.backend.user.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login (LoginRequest loginRequest) {
        return null;
    }

    public AuthResponse register (RegisterRequest request) {

        Usuario user = Usuario.builder()
            .usuario(request.getUsuario())
            .contrasena(request.getContrasena())
            .idiomaDominado(request.getIdiomaDominado())
            .idiomaAprender(request.getIdiomaAprender())
            .role(Role.USER)
            .build();
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

}
