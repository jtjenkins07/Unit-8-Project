package com.example.ContactManager.Contact;

import com.example.ContactManager.Group.Group;
import com.example.ContactManager.Group.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;}

    @Autowired
    GroupRepository groupRepository;

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public void addNewContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(Long contactId){
        boolean exists = contactRepository.existsById(contactId);
        if(!exists){
            throw new IllegalStateException("Contact ID: " + contactId + "does not exist.");
        }
        contactRepository.deleteById(contactId);
    }

    @Transactional
    public void updateContact(Long contactId, String name){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + "does not exist."));

        if(name != null && !name.isEmpty() && !Objects.equals(contact.getName(), name)){
            contact.setName(name);
        }
    }

    public void assignGroupToContact(Long contactId, Long groupId){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + "does not exist."));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("Group ID: " + groupId + "does not exist."));
    }
}
