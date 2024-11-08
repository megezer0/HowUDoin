package sabanciuniv.edu.howudoin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sabanciuniv.edu.howudoin.model.Group;

public interface GroupsRepository extends MongoRepository<Group, String> {


}
