package sabanciuniv.edu.howudoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.service.MessagesService;

import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    //-	POST /messages/send: Send a message to a friend
    @PostMapping("/messages/send")
    public Message sendMessage(@RequestBody Map<String, Object> request) {
        String from = (String) request.get("senderEmail");
        String to = (String) request.get("receiverEmail");
        String content = (String) request.get("content");

        return messagesService.sendMessage(from, to, content);
    }

    //-	GET /messages: Retrieve conversation history
    //Example test URL: /friends?user1Email=C@gmail.com&user2Email=D@gmail.com
    @GetMapping("/messages")
    public List<Message> getMessage(@RequestParam String user1Email, @RequestParam String user2Email) {

        return messagesService.getConversation(user1Email, user2Email);
    }
}

/*
-	POST /messages/send: Send a message to a friend
-	GET /messages: Retrieve conversation history
*/