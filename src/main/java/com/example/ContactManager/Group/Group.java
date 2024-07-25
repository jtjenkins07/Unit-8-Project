package com.example.ContactManager.Group;

import com.example.ContactManager.Contact.Contact;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name= "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
