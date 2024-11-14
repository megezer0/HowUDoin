package sabanciuniv.edu.howudoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sabanciuniv.edu.howudoin.model.Group;
import sabanciuniv.edu.howudoin.repository.GroupsRepository;
import sabanciuniv.edu.howudoin.service.GroupsService;

@RestController
public class GroupsController {

    private GroupsService groupsService;

    @PostMapping("/groups/create")
    public void createGroup(){
        Group newGroup = new Group();
        groupsService.createGroup(newGroup);
    }

    @PostMapping("")
    public void addMember(){

    }

    @PostMapping("")
    public void sendToGroup(){

    }

    @GetMapping("")
    public void groupMessages(){

    }

    @GetMapping("")
    public void groupMembers(){

    }

}

/*
-	POST /groups/create: Creates a new group with a given name and members.
-	POST /groups/:groupId/add-member: Adds a new member to an existing group.
-	POST /groups/:groupId/send: Sends a message to all members of the specified group.
-	GET /groups/:groupId/messages: Retrieves the message history for the specified group.
-	GET /groups/:groupId/members: Retrieves the list of members for the specified group.
*/
