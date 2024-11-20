package sabanciuniv.edu.howudoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.model.UserModel;
import sabanciuniv.edu.howudoin.repository.UsersRepository;

import java.util.List;
//Also import PasswordEncoder and JwtTokenProvider

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    //Registers a new user.
    public UserModel registerUser(UserModel user) throws Exception{
        if(usersRepository.existsByEmail(user.getEmail())) {
            throw new Exception("User already exists");
        }

        return usersRepository.save(user);
    }

    //Adds the receiver’s ID to pendingFriendRequests in the User document.
    public boolean sendFriendRequest(String senderEmail, String recipientEmail){
        UserModel sender = usersRepository.findByEmail(senderEmail);
        UserModel recipient = usersRepository.findByEmail(recipientEmail);

        if (sender == null || recipient == null) {
            System.out.println("Sender or recipient not found");
            return false; // User not found
        }

        UserModel.UserDTO recipientDTO = new UserModel.UserDTO(recipient.getId(), recipient.getEmail(), recipient.getFirstName(), recipient.getLastName());
        UserModel.UserDTO senderDTO = new UserModel.UserDTO(sender.getId(), sender.getEmail(), sender.getFirstName(), sender.getLastName());

        // Check if already friends
        boolean alreadyFriends = recipient.getFriends().contains(senderDTO);
        if (alreadyFriends) {
            System.out.println(sender.getEmail() + " and " + recipientDTO.getEmail() +" are already friends");
            return false;
        }

        // Check if request already exists
        boolean inPendingList =recipient.getPendingFriendsRequest().contains(senderDTO);
        if (inPendingList) {
            System.out.println("Sender and recipient are already pending requests");
            return false;
        }

        // Add to recipient's pending requests
        recipient.getPendingFriendsRequest().add(senderDTO);

        // Save changes
        usersRepository.save(recipient);
        System.out.println("Friend request sent to " + recipient.getFirstName() + " " + recipient.getLastName());
        return true;
    }

    //Moves the requester’s ID from pendingFriendRequests to friends list.
    public boolean acceptFriendRequest(String senderEmail, String accepterEmail, boolean accept){
        UserModel sender = usersRepository.findByEmail(senderEmail);
        UserModel accepter = usersRepository.findByEmail(accepterEmail);

        if (sender == null || accepter == null) {
            System.out.println("Sender or accepter not found");
            return false; // User not found
        }

        UserModel.UserDTO accepterDTO = new UserModel.UserDTO(accepter.getId(), accepter.getEmail(), accepter.getFirstName(), accepter.getLastName());
        UserModel.UserDTO senderDTO = new UserModel.UserDTO(sender.getId(), sender.getEmail(), sender.getFirstName(), sender.getLastName());

        boolean alreadyFriends = accepter.getFriends().contains(senderDTO);
        if (alreadyFriends) {
            System.out.println(sender.getEmail() + " and " + accepter.getEmail() +" are already friends");
            return false;
        }
        if(accepter.getPendingFriendsRequest().isEmpty()){
            System.out.println("There is no friend request");
            return false;
        }

        // Check if the request exists
        boolean inPendingList = accepter.getPendingFriendsRequest().contains(senderDTO);
        if(inPendingList){
            if (accept) {
                // Add each to the other's friends list
                sender.getFriends().add(accepterDTO);
                accepter.getFriends().add(senderDTO);

                // Remove the request1
                accepter.getPendingFriendsRequest().remove(senderDTO);

                // Save changes
                usersRepository.save(sender);
                usersRepository.save(accepter);
                System.out.println("Friend request accepted");
                return true;
            }
            else{
                // Remove the request1
                accepter.getPendingFriendsRequest().remove(senderDTO);

                usersRepository.save(accepter);
                System.out.println("Friend request rejected");
                return false;
            }
        }
        else{

        System.out.println("There is no friend request from " + sender.getEmail());
        return false; // Request doesn't exist
        }
    }

    //Retrieves a user’s friend list.
    public List<UserModel.UserDTO> getFriendList(String userEmail){
        UserModel user = usersRepository.findByEmail(userEmail);
        return user.getFriends();
    }
}
