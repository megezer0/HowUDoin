package sabanciuniv.edu.howudoin.service;

import sabanciuniv.edu.howudoin.model.User;
import sabanciuniv.edu.howudoin.repository.UsersRepository;
//Also import PasswordEncoder and JwtTokenProvider

public class UsersService {

    //Registers a new user.
    void registerUser(User user) {

    }

    //Authenticates user and generates a JWT.
    void authenticateUser(String email, String password) {

    }

    //Retrieves a user’s friend list.
    void getFriendList(String userId){

    }

    //Adds the receiver’s ID to pendingFriendRequests in the User document.
    void sendFriendRequest(String senderId, String receiverId){

    }

    //Moves the requester’s ID from pendingFriendRequests to friends list.
    void acceptFriendRequest(String userId, String requesterId){

    }

}
