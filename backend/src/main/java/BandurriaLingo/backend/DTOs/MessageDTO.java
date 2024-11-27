package BandurriaLingo.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageDTO {
    private int id;
    private String sender;
    private String content;
    private int chat_id;
}
