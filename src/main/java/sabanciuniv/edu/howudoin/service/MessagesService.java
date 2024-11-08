package sabanciuniv.edu.howudoin.service;

import sabanciuniv.edu.howudoin.repository.*;
import sabanciuniv.edu.howudoin.model.*;

public class MessagesService {

    //Saves a direct message to the Message collection.
    void sendMessage(String senderId, String receiverId, String content){

    }

    //Retrieves messages between two users, filtering by senderId and receiverId.
    void getConversation(String userId1, String userId2){

    }

    //Saves a group message to the Message collection with the group ID.
    void sendGroupMessage(String senderId, String groupId, String content){

    }

    //Retrieves all messages for a specific group.
    void getGroupMessages(String groupId){

    }

}
