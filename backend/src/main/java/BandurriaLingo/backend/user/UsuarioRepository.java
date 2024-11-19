package BandurriaLingo.backend.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);

    List<Usuario> findTop10ByOrderByNivelDesc();
    List<Usuario> findTop3ByOrderByNivelDesc();
}
