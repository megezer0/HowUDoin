package sabanciuniv.edu.howudoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @JsonIgnore
    private List<UserDTO> friends = new ArrayList<>();
    @JsonIgnore
    private List<UserDTO> pendingFriendsRequest = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UserDTO {
        private String id;
        private String email;
        private String firstName;
        private String lastName;

    }
}
