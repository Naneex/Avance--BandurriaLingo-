package BandurriaLingo.backend.Controller;

import BandurriaLingo.backend.DTOs.MessageDTO;
import BandurriaLingo.backend.Service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    public ChatMessageController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat/{chatId}/sendMessage")
    public void sendMessageToChat(@DestinationVariable int chatId, @Payload MessageDTO messageDTO){
        System.out.println("Message: " + messageDTO.getContent());
        System.out.println("Sender: " + messageDTO.getSender());
        System.out.println("To Chat ID: " + messageDTO.getChat_id());
        System.out.println("/topic/chat/" + chatId);

        messageService.saveMessage(chatId, messageDTO);
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, messageDTO);
    }
}
