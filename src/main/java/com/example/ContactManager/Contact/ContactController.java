package com.example.ContactManager.Contact;

import com.example.ContactManager.Priority.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping("/{contactId}")
    public Contact getContactById(@PathVariable("contactId") Long contactId){
        return contactService.getContactById(contactId);
    }


    @PostMapping
    public void createNewContact(@RequestBody Contact contact){
        contactService.addNewContact(contact);
    }

    @DeleteMapping(path = "{contactId}")
    public void deleteContact(@PathVariable("contactId") Long contactId){
        contactService.deleteContact(contactId);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable("contactId") Long contactId,
            @RequestBody Contact contact) {
        Contact updatedContact = contactService.updateContact(contactId, contact);
        return ResponseEntity.ok(updatedContact);
    }


    @PutMapping("/{contactId}/contact/{groupId}")
    Priority assignContactToGroup(
            @PathVariable Long contactId,
            @PathVariable Long groupId){
        contactService.assignPriorityToContact(contactId, groupId);
        return null;
    }
}
