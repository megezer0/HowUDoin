package sabanciuniv.edu.howudoin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sabanciuniv.edu.howudoin.model.Message;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Message, String> {
    List<Message> findAllByGroupId(String groupId);
}
