package sabanciuniv.edu.howudoin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponse {
    private String token;
    private String email;
}
