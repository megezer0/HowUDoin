package sabanciuniv.edu.howudoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sabanciuniv.edu.howudoin.model.Group;
import sabanciuniv.edu.howudoin.model.Message;
import sabanciuniv.edu.howudoin.model.UserModel;
import sabanciuniv.edu.howudoin.service.GroupsService;

import java.util.List;
import java.util.Map;

@RestController
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    //-	POST /groups/create: Creates a new group with a given name and members.
    @PostMapping("/groups/create")
    public Group createGroup(@RequestBody Map<String, Object> request){

        String name = (String) request.get("name");
        String adminEmail = (String) request.get("adminEmail");
        Group group = new Group();
        List<String> members = (List<String>) request.get("memberEmails");

        return groupsService.createGroup(name, adminEmail, members);
    }

    //-	POST /groups/:groupId/add-member: Adds a new member to an existing group.
    @PostMapping("/groups/{groupId}/add-member")
    public Group addMember(@PathVariable String groupId, @RequestBody Map<String, String> request){
        String userEmail = request.get("userEmail");
        return groupsService.addMemberToGroup(groupId, userEmail);
    }

    //-	POST /groups/:groupId/send: Sends a message to all members of the specified group.
    @PostMapping("/groups/{groupId}/send")
    public Message sendToGroup(@PathVariable String groupId, @RequestBody Map<String, String> request){
        String senderEmail = request.get("senderEmail");
        String content = request.get("content");

        return groupsService.sendMessageToGroup(senderEmail, groupId, content);
    }

    //-	GET /groups/:groupId/messages: Retrieves the message history for the specified group.
    @GetMapping("/groups/{groupId}/messages")
    public List<Message> groupMessages(@PathVariable String groupId){
        return groupsService.getGroupMessages(groupId);
    }

    //-	GET /groups/:groupId/members: Retrieves the list of members for the specified group.
    @GetMapping("/groups/{groupId}/members")
    public List<UserModel.UserDTO> groupMembers(@PathVariable String groupId){
        return groupsService.getGroupMembers(groupId);
    }
}

/*
-	POST /groups/create: Creates a new group with a given name and members.
-	POST /groups/:groupId/add-member: Adds a new member to an existing group.
-	POST /groups/:groupId/send: Sends a message to all members of the specified group.
-	GET /groups/:groupId/messages: Retrieves the message history for the specified group.
-	GET /groups/:groupId/members: Retrieves the list of members for the specified group.
*/
