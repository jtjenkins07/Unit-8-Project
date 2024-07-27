package com.example.ContactManager.Contact;

import com.example.ContactManager.Group.Group;
import com.example.ContactManager.Group.GroupRepository;
import com.example.ContactManager.Priority.Priority;
import com.example.ContactManager.Priority.PriorityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;}

    @Autowired
    PriorityRepository priorityRepository;

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public Contact getContactById(Long contactId){
        Optional<Contact> contact = contactRepository.findById(contactId);
        return contact.orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + " could not be found."));
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
    public void updateContact(Long contactId, String name, String phone, String email){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + "does not exist."));

        if(name != null && !name.isEmpty()){
            contact.setName(name);
        }
        if(phone != null && !phone.isEmpty()){
            contact.setPhone(phone);
        }
        if(email != null && !email.isEmpty()){
            contact.setEmail(email);
        }
        contactRepository.save(contact);
    }

    public void assignPriorityToContact(Long contactId, Long priorityId){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + "does not exist."));
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new IllegalStateException("Priority ID: " + priorityId + "does not exist."));
        contact.assignPriority(priority);
        contactRepository.save(contact);
    }
}
