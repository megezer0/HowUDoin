package sabanciuniv.edu.howudoin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginRequest {
    private String email;
    private String password;
}
