package BandurriaLingo.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ChatDTO {
    private int id;
    private List<MessageDTO> messages;
}
