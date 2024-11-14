package sabanciuniv.edu.howudoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String id;
    private User senderUser;
    private User receiverUser;
    private Group recieverGroup;
    private String messageContent;
    private String timestamp;
}
