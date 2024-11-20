package sabanciuniv.edu.howudoin.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    private String id;
    private UserModel.UserDTO senderUser;
    private UserModel.UserDTO recieverUser;
    private Group recieverGroup;
    private String messageContent;
    @CreatedDate
    private Instant timestamp;
}
