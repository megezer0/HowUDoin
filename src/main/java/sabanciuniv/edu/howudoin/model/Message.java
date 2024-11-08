package sabanciuniv.edu.howudoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    String id;
    int senderId;
    int receiverId;
    int groupId;
    String content;
    String timestamp;
}
