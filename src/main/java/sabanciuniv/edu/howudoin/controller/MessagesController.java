package sabanciuniv.edu.howudoin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.service.MessagesService;

@RestController
public class MessagesController {

    private MessagesService messagesService;

    @PostMapping("/messages/send")
    public void sendMessage() {
        Message message = new Message();
        //messagesService.sendMessage();
    }

    @GetMapping("/messages")
    public void getMessage() {

    }
}

/*
-	POST /messages/send: Send a message to a friend
-	GET /messages: Retrieve conversation history
*/