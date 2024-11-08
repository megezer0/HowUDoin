package sabanciuniv.edu.howudoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    List<User> friends;
    List<User> pendingFriendsRequest;

}
