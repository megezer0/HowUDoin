package sabanciuniv.edu.howudoin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sabanciuniv.edu.howudoin.model.Group;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.model.UserModel;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Message, String> {
    List<Message> findAllByRecieverGroup(Group group);
    List<Message> findAllBySenderUserAndRecieverUser(UserModel.UserDTO user1Id, UserModel.UserDTO user2Id);

    List<Message> findAllByRecieverGroupOrderByTimestampAsc(Group group);
    List<Message> findAllByRecieverGroupOrderByTimestampDesc(Group group);

}
