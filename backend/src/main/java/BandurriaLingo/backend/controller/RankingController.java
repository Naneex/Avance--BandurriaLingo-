package BandurriaLingo.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import BandurriaLingo.backend.user.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping("/top")
    public ResponseEntity<List<UsuarioRankingDTO>> getTopRanking() {
        List<UsuarioRankingDTO> topRanking = usuarioRepository.findTop3ByOrderByNivelDesc()
            .stream()
            .map(user -> new UsuarioRankingDTO(user.getUsuario(), user.getImagen(), user.getNivel()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(topRanking);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioRankingDTO>> getRankingList() {
        List<UsuarioRankingDTO> rankingList = usuarioRepository.findTop10ByOrderByNivelDesc()
            .stream()
            .limit(10)
            .map(user -> new UsuarioRankingDTO(user.getUsuario(), user.getImagen(), user.getNivel()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(rankingList);
    }
}
