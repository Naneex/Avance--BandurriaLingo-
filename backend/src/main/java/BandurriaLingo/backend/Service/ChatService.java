package BandurriaLingo.backend.Service;

import BandurriaLingo.backend.Model.Chat;
import BandurriaLingo.backend.Repository.ChatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public List<Chat> getAllChats (){
        return chatRepository.findAll();
    }

    public Chat findById(int chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat Not Found with ID: " + chatId));
    }

    public Chat save(Chat chat){
        return chatRepository.save(chat);
    }

    public Chat findOrCreateChatBeetwenIds(int id1, int id2){
        return chatRepository.findChatBetweenUsers(id1, id2)
                .orElseGet(() -> {
                    Chat newChat = new Chat();
                    newChat.setIntegrantes(List.of(id1,id2));
                    return chatRepository.save(newChat);
                });
    }
}
