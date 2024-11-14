
package BandurriaLingo.backend.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasena()));
        UserDetails user= userRepository.findByUsuario(request.getUsuario()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register (RegisterRequest request) {

        Usuario user = Usuario.builder()
            .usuario(request.getUsuario())
            .contrasena(passwordEncoder.encode(request.getContrasena()))
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
