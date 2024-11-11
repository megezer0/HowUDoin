package sabanciuniv.edu.howudoin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sabanciuniv.edu.howudoin.model.User;

public interface UsersRepository extends MongoRepository<User, String> {
}
