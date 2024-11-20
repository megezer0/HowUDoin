package sabanciuniv.edu.howudoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.model.Group;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.model.UserModel;
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
    public Group createGroup(String name, String adminEmail, List<String> memberEmails) {
        Group newGroup = new Group();
        UserModel admin = usersRepository.findByEmail(adminEmail);
        List<UserModel.UserDTO> members = new ArrayList<>();

        UserModel.UserDTO adminDTO = new UserModel.UserDTO(admin.getId(), admin.getEmail(), admin.getFirstName(), admin.getLastName());

        members.add(adminDTO);
        for (String memberEmail : memberEmails) {
            UserModel member = usersRepository.findByEmail(memberEmail);
            UserModel.UserDTO memberDTO = new UserModel.UserDTO(member.getId(), member.getEmail(), member.getFirstName(), member.getLastName());
            members.add(memberDTO);
        }

        newGroup.setName(name);
        newGroup.setAdmin(adminDTO);
        newGroup.setMembers(members);

        return groupsRepository.save(newGroup);
    }

    //Adds a user ID to the members list in a group document.
    public Group addMemberToGroup(String groupId, String userEmail){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();

        UserModel newMember = usersRepository.findByEmail(userEmail);
        if(newMember == null) {
            throw new IllegalArgumentException("User with email " + userEmail + " not found");
        }
        UserModel.UserDTO memberDTO = new UserModel.UserDTO(newMember.getId(), newMember.getEmail(), newMember.getFirstName(), newMember.getLastName());

        group.getMembers().add(memberDTO);
        return groupsRepository.save(group);
    }

    public Message sendMessageToGroup(String senderEmail, String groupId, String content){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();

        UserModel sender = usersRepository.findByEmail(senderEmail);
        if (sender == null) {
            throw new IllegalArgumentException("User with ID " + senderEmail + " not found");
        }
        UserModel.UserDTO senderDTO = new UserModel.UserDTO(sender.getId(), sender.getEmail(), sender.getFirstName(), sender.getLastName());


        Message newMessage = new Message();
        newMessage.setSenderUser(senderDTO);
        newMessage.setRecieverGroup(group);
        newMessage.setMessageContent(content);

        return messagesRepository.save(newMessage);
    }

    //Retrieves all members in a group.
    public List<UserModel.UserDTO> getGroupMembers(String groupId){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();

        return group.getMembers();
    }

    //Retrieves all messages in a group.
    public List<Message> getGroupMessages(String groupId){
        Optional<Group> groupOptional = groupsRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found");
        }
        Group group = groupOptional.get();

        return messagesRepository.findAllByRecieverGroupOrderByTimestampDesc(group);
    }
}
