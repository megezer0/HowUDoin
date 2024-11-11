package sabanciuniv.edu.howudoin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @PostMapping("/register")
    public void register(){

    }

    @PostMapping("/login")
    public void login(){

    }

    @PostMapping("/friends/add")
    public void friendRequest(){

    }

    @PostMapping("/friends/accept")
    public void acceptFriendRequest(){

    }

    @GetMapping("/friends")
    public void friendsList(){

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