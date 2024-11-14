package sabanciuniv.edu.howudoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private String groupId;
    private String name;
    private User admin;
    private List<User> members;
}
