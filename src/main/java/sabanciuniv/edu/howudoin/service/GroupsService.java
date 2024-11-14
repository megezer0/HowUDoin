package sabanciuniv.edu.howudoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.model.Group;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.model.User;
import sabanciuniv.edu.howudoin.repository.GroupsRepository;
import sabanciuniv.edu.howudoin.repository.MessagesRepository;
import sabanciuniv.edu.howudoin.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private MessagesRepository messagesRepository;


    //Creates a new group document in Group.
    public void createGroup(String name, String adminId, List<User> members) {
        Group newGroup = new Group();

        Optional<User> adminOptional = usersRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + adminId + " not found");
        }
        User admin = adminOptional.get();

        newGroup.setAdmin(admin);
        newGroup.setName(name);
        newGroup.setMembers(members);

        groupsRepository.save(newGroup);
    }

    //Adds a user ID to the members list in a group document.
    public void addMemberToGroup(String groupId, String userId){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }

        Optional<User> userOptional = usersRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        Group group = groupOptional.get();
        User user = userOptional.get();

        group.getMembers().add(user);
        groupsRepository.save(group);
    }

    public void sendMessageToGroup(String senderId, String groupId, String content, String timestamp){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();

        Optional<User> senderOptional = usersRepository.findById(senderId);
        if (senderOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + senderId + " not found");
        }
        User sender = senderOptional.get();

        Message newMessage = new Message();
        newMessage.setMessageContent(content);
        newMessage.setTimestamp(timestamp);
        newMessage.setRecieverGroup(group);
        newMessage.setSenderUser(sender);

        messagesRepository.save(newMessage);
    }

    //Retrieves all members in a group.
    public List<User> getGroupMembers(String groupId){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();

        return group.getMembers();
    }

    //Retrieves all messages in a group.
    public List<Message> getGroupMessages(String groupId){
        /*Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();
        */

        return messagesRepository.findAllByGroupId(groupId);
    }
}
