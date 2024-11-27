package BandurriaLingo.backend.Service;

import BandurriaLingo.backend.DTOs.MessageDTO;
import BandurriaLingo.backend.Model.Chat;
import BandurriaLingo.backend.Model.Message;
import BandurriaLingo.backend.Repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;

    public Message saveMessage (int chatId, MessageDTO messageDTO){
        Chat chat = chatService.findById(chatId);

        Message message = new Message (
                chat,
                messageDTO.getSender(),
                messageDTO.getContent()
        );

        return messageRepository.save(message);
    }
}

