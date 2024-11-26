package BandurriaLingo.backend.Controller;

import BandurriaLingo.backend.Model.Chat;
import BandurriaLingo.backend.Service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/chats/")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<Chat> getAllChats(){
        return chatService.getAllChats();
    }

    @PostMapping
    public Chat createChat(@RequestBody Chat chat){
        return chatService.save(chat);
    }
    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable int id){
        return chatService.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/between")
    public Chat getBetweenUsers(
            @RequestParam int id1,
            @RequestParam int id2) {
        Chat chat;
        chat = chatService.findOrCreateChatBeetwenIds(id1, id2);
        return chat;
    }
}
