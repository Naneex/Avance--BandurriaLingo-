package BandurriaLingo.backend.pares;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pares_palabras")
public class PalabraPar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palabraEs;
    private String palabraEn;

    @ManyToOne
    @JoinColumn(name = "set_id")
    @JsonBackReference
    private SetPalabras setPalabras;

    
}
