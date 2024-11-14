package sabanciuniv.edu.howudoin.service;

import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.model.Group;
import sabanciuniv.edu.howudoin.model.User;
import sabanciuniv.edu.howudoin.repository.GroupsRepository;
import sabanciuniv.edu.howudoin.repository.UsersRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
public class GroupsService {

    private final UsersRepository usersRepository;
    private GroupsRepository groupsRepository;

    public GroupsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //Creates a new group document in Group.
    public void createGroup(Group newGroup){
        groupsRepository.save(newGroup);
    }

    //Adds a user ID to the members list in a group document.
    public void addMemberToGroup(String groupId, String userId){
        Optional<Group> group = groupsRepository.findById(groupId);
        Optional<User> user = usersRepository.findById(userId);
        group.get().getMembers().add(user);
    }

    //Retrieves all members in a group.
    public List<Object> getGroupMembers(String groupId){
        Optional<Group> group = groupsRepository.findById(groupId);
        return group.get().getMembers(); 
    }
}
