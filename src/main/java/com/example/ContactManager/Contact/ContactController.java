package com.example.ContactManager.Contact;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void createNewContact(@RequestBody Contact contact){
        contactService.addNewContact(contact);
    }

    @DeleteMapping(path = "{contactId}")
    public void deleteContact(@PathVariable("contactId") Long contactId){
        contactService.deleteContact(contactId);
    }

    @PutMapping(path = "{contactId}")
    public void updateContact(
            @PathVariable("contactId") Long contactId, @RequestParam(required=false) String name) {
        contactService.updateContact(contactId,name);
    }
}
