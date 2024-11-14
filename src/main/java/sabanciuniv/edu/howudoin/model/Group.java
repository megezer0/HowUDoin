package sabanciuniv.edu.howudoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    String id;
    String name;
    int adminId;
    List<Object> members;
    //List<Message> messages; -> Not sure about this one

}
