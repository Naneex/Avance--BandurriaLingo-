package BandurriaLingo.backend.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRankingDTO {
    private String usuario;
    private String imagen;
    private Integer nivel;
}