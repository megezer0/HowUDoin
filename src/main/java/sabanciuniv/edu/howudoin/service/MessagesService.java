package sabanciuniv.edu.howudoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.model.UserModel;
import sabanciuniv.edu.howudoin.repository.MessagesRepository;
import sabanciuniv.edu.howudoin.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessagesService {
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private UsersRepository usersRepository;

    //Saves a direct message to the Message collection.
    public Message sendMessage(String senderEmail, String recieverEmail, String content){
        Message newMessage = new Message();

        UserModel sender = usersRepository.findByEmail(senderEmail);
        if (sender == null) {
            throw new IllegalArgumentException("User with email " + senderEmail + " not found");
        }
        UserModel.UserDTO senderDTO = new UserModel.UserDTO(sender.getId(), sender.getEmail(), sender.getFirstName(), sender.getLastName());

        UserModel reciever = usersRepository.findByEmail(recieverEmail);
        if (reciever == null) {
            throw new IllegalArgumentException("User with email " + recieverEmail + " not found");
        }
        UserModel.UserDTO recieverDTO = new UserModel.UserDTO(reciever.getId(), reciever.getEmail(), reciever.getFirstName(), reciever.getLastName());

        if(sender.getFriends().contains(recieverDTO) || reciever.getFriends().contains(senderDTO)){
            newMessage.setSenderUser(senderDTO);
            newMessage.setRecieverUser(recieverDTO);
            newMessage.setMessageContent(content);
            return messagesRepository.save(newMessage);
        }
        else{
            throw new IllegalArgumentException("Users with email " + recieverEmail + " and " + senderEmail + " are not friend");
        }
    }

    //Retrieves messages between two users, filtering by senderId and receiverId.
    public List<Message> getConversation(String user1Email, String user2Email){
        UserModel user1 = usersRepository.findByEmail(user1Email);
        if (user1 == null) {
            throw new IllegalArgumentException("User with ID " + user1Email + " not found");
        }
        UserModel.UserDTO user1DTO = new UserModel.UserDTO(user1.getId(), user1.getEmail(), user1.getFirstName(), user1.getLastName());

        UserModel user2 = usersRepository.findByEmail(user2Email);
        if (user2 == null) {
            throw new IllegalArgumentException("User with ID " + user2Email + " not found");
        }
        UserModel.UserDTO user2DTO = new UserModel.UserDTO(user2.getId(), user2.getEmail(), user2.getFirstName(), user2.getLastName());

        List<Message> betweenUser1AndUser2 = new ArrayList<>();
        for(Message message : messagesRepository.findAll()){
            if(message.getSenderUser().equals(user1DTO) && message.getRecieverUser().equals(user2DTO) || message.getSenderUser().equals(user2DTO) && message.getRecieverUser().equals(user1DTO)){
                betweenUser1AndUser2.add(message);
            }
        }

        return betweenUser1AndUser2;
    }
}
