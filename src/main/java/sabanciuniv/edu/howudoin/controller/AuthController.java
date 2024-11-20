package sabanciuniv.edu.howudoin.controller;


import sabanciuniv.edu.howudoin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import sabanciuniv.edu.howudoin.security.JwtHelperUtils;


@RestController
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelperUtils jwtHelper;

    //-	POST /login: Authenticate and login a user (with email and password). It creates a JWT Token
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        this.doAuthenticate(loginRequest.getEmail(), loginRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);
        LoginResponse response = LoginResponse.builder().token(token).email(userDetails.getUsername()).build();
        return ResponseEntity.ok(response);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }
}