package sabanciuniv.edu.howudoin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sabanciuniv.edu.howudoin.model.Message;

public interface MessagesRepository extends MongoRepository<Message, String> {

}
