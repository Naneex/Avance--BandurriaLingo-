package BandurriaLingo.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
    @ElementCollection
    @NonNull
    private List<Integer> integrantes;
}
