package BandurriaLingo.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String usuario;
    String contrasena;
    String idiomaDominado;
    String idiomaAprender;

}
