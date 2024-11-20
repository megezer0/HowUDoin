package sabanciuniv.edu.howudoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private String groupId;
    private String name;
    private UserModel.UserDTO admin;
    private List<UserModel.UserDTO> members = new ArrayList<>();
}
