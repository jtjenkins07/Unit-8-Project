package com.example.ContactManager.Group;

import com.example.ContactManager.Contact.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "groups")
public class Group {
    @Id
    @SequenceGenerator(
            name="group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name="Contact_Group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name ="contact_id")
    )
    private Set<Contact> contacts = new HashSet<>();

    public Group() {
    }

    public Group(Long id, String name, Set<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public Group(String name, Set<Contact> contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void assignContact(Contact contact){
        contacts.add(contact);
    }
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
