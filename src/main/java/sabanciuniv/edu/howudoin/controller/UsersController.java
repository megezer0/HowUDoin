package sabanciuniv.edu.howudoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sabanciuniv.edu.howudoin.model.UserModel;
import sabanciuniv.edu.howudoin.service.UsersService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    //-	POST /register: Register a new user (with name, last name, email, password)
    @PostMapping("/register")
    public UserModel register(@RequestBody Map<String, String> request) throws Exception {
        UserModel user = new UserModel();
        user.setFirstName(request.get("firstName"));
        user.setLastName(request.get("lastName"));
        user.setEmail(request.get("email"));
        user.setPassword(request.get("password"));
        return usersService.registerUser(user);
    }

    //-	POST /friends/add: Send a friend request
    @PostMapping("/friends/add")
    public boolean friendRequest(@RequestBody Map<String, String> request){
        String senderEmail = request.get("senderEmail");
        String recipientEmail = request.get("recipientEmail");
        return usersService.sendFriendRequest(senderEmail, recipientEmail);
    }

    //-	POST /friends/accept: Accept a friend request ( If there is a friend request)
    @PostMapping("/friends/accept")
    public boolean acceptFriendRequest(@RequestBody Map<String, String> request){
        String senderEmail = request.get("senderEmail");
        String accepterEmail = request.get("accepterEmail");
        String response = request.get("accept");

        boolean accept = Objects.equals(response, "accepted");
        return usersService.acceptFriendRequest(senderEmail, accepterEmail, accept);
    }

    //-	GET /friends: Retrieve friend list
    //Example test URL: /friends?userEmail=D@gmail.com
    @GetMapping("/friends")
    public List<UserModel.UserDTO> friendsList(@RequestParam String userEmail) {
        return usersService.getFriendList(userEmail);
    }
}

/*
Public Endpoints
-	POST /register: Register a new user (with name, last name, email, password)
-	POST /login: Authenticate and login a user (with email and password)
Secure Endpoints
-	POST /friends/add: Send a friend request
-	POST /friends/accept: Accept a friend request ( If there is a friend request)
-	GET /friends: Retrieve friend list
*/