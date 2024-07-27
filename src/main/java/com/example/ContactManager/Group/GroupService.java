package com.example.ContactManager.Group;


import com.example.ContactManager.Contact.Contact;
import com.example.ContactManager.Contact.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups(){
        return groupRepository.findAll();
    }

    public Group getGroupById(Long groupId){
        Optional<Group> group = groupRepository.findById(groupId);
        return group.orElseThrow(() -> new IllegalStateException("Group ID: " + groupId + " could not be found."));
    }

    public void addNewGroup(Group group){
        groupRepository.save(group);
    }

    public void deleteGroup(Long groupId){
        boolean exists = groupRepository.existsById(groupId);
        if (!exists) {
            throw new IllegalStateException("Group ID: " + groupId + "does not exist");
        }
        groupRepository.deleteById(groupId);
    }

    @Transactional
    public void updateGroup(Long groupId, String name){
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("Group Id: " + groupId + "does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(group.getName(), name)){
            group.setName(name);
        }
    }
}
