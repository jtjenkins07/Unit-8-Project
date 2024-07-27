package com.example.ContactManager.Group;


import com.example.ContactManager.Contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getGroups(){
        return groupService.getGroups();
    }

    @GetMapping("/{groupId}")
    public Group getGroupById(@PathVariable("groupId") Long groupId){
        return groupService.getGroupById(groupId);
    }

    @PostMapping
    public void createNewGroup(@RequestBody Group group){
        groupService.addNewGroup(group);
    }

    @DeleteMapping(path="{groupId}")
    public void deleteGroup(@PathVariable("groupId") Long groupId){
        groupService.deleteGroup(groupId);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Group> updateGroup(
            @PathVariable("groupId") Long groupId,
            @RequestBody Group group) {
        Group updatedGroup = groupService.updateGroup(groupId, group);
        return ResponseEntity.ok(updatedGroup);
    }

}
