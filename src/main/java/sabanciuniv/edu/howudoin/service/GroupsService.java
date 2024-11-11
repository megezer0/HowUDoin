package sabanciuniv.edu.howudoin.service;

import org.springframework.stereotype.Service;
import sabanciuniv.edu.howudoin.repository.GroupsRepository;

import java.util.List;

@Service
public class GroupsService {

    private GroupsRepository groupsRepository;

    //Creates a new group document in Group.
    public void createGroup(String adminId, String groupName, List<String> memberIds){

    }

    //Adds a user ID to the members list in a group document.
    public void addMemberToGroup(String groupId, String userId){

    }

    //Retrieves all members in a group.
    public void getGroupMembers(String groupId){

    }
}
