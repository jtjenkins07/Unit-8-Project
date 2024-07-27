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
    public Contact updateContact(Long contactId, Contact contact) {
        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + " does not exist."));

        if (contact.getName() != null && !contact.getName().isEmpty() && !Objects.equals(existingContact.getName(), contact.getName())) {
            existingContact.setName(contact.getName());
        }

        if (contact.getPhone() != null && !contact.getPhone().isEmpty() && !Objects.equals(existingContact.getPhone(), contact.getPhone())) {
            existingContact.setPhone(contact.getPhone());
        }

        if (contact.getEmail() != null && !contact.getEmail().isEmpty() && !Objects.equals(existingContact.getEmail(), contact.getEmail())) {
            existingContact.setEmail(contact.getEmail());
        }

        // Save the updated contact
        return contactRepository.save(existingContact);
    }

    public void assignPriorityToContact(Long contactId, Long priorityId){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + "does not exist."));
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new IllegalStateException("Priority ID: " + priorityId + "does not exist."));
        contact.assignPriority(priority);
        contactRepository.save(contact);
    }
}
