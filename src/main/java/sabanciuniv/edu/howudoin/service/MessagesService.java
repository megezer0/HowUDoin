package sabanciuniv.edu.howudoin.service;

import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.repository.MessagesRepository;
import sabanciuniv.edu.howudoin.model.*;

@Service
public class MessagesService {

    private MessagesRepository messagesRepository;

    //Saves a direct message to the Message collection.
    public void sendMessage(String senderId, String receiverId, String content){

    }

    //Retrieves messages between two users, filtering by senderId and receiverId.
    public void getConversation(String userId1, String userId2){

    }

    //Saves a group message to the Message collection with the group ID.
    public void sendGroupMessage(String senderId, String groupId, String content){

    }

    //Retrieves all messages for a specific group.
    public void getGroupMessages(String groupId){

    }

}
