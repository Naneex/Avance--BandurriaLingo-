package BandurriaLingo.backend.pares;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/parespalabras")
@RequiredArgsConstructor
public class PalabrasController {

    private final SetPalabrasRepository setPalabrasRepository;



    @GetMapping
    public ResponseEntity<List<SetPalabras>> getAllSets() {
        List<SetPalabras> sets = setPalabrasRepository.findAll();
        System.out.println("Número de sets encontrados: " + sets.size());
        for (SetPalabras set : sets) {
            System.out.println("Set: " + set.getNombre());
            if (set.getPalabras() != null) {
                for (PalabraPar palabra : set.getPalabras()) {
                    System.out.println("Palabra: " + palabra.getPalabraEs() + " - " + palabra.getPalabraEn());
                }
            }
        }
        return ResponseEntity.ok(sets);
    }

    
}
