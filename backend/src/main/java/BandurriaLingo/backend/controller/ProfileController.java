package BandurriaLingo.backend.controller;


import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import BandurriaLingo.backend.jwt.JwtService;
import BandurriaLingo.backend.user.Usuario;
import BandurriaLingo.backend.user.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;



@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<Usuario> getLoggedUserProfile(HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request); 
        String username = jwtService.getUsuarioFromToken(token); 

        Usuario user = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(user); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getProfileById(@PathVariable Long id) {
        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/me")
    public ResponseEntity<Map<String, String>> updateProfile(HttpServletRequest request, @RequestBody Usuario updatedUser) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(403).build();
        }

        token = token.substring(7);
        String username = jwtService.getUsuarioFromToken(token);

        System.out.println("Token recibido: " + token);
        System.out.println("Usuario extraído del token: " + username);

        Usuario existingUser = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        
        existingUser.setUsuario(updatedUser.getUsuario());
        existingUser.setIdiomaDominado(updatedUser.getIdiomaDominado());
        existingUser.setIdiomaAprender(updatedUser.getIdiomaAprender());
        existingUser.setRango(updatedUser.getRango());
        existingUser.setNivel(updatedUser.getNivel());
        existingUser.setExp(updatedUser.getExp());
        existingUser.setImagen(updatedUser.getImagen());

        Usuario savedUser = usuarioRepository.save(existingUser);

         String newToken = jwtService.getToken(savedUser);

        Map<String, String> response = new HashMap<>();
        response.put("token", newToken);
        return ResponseEntity.ok(response);

    }
}