package com.example.ContactManager.Contact;

import com.example.ContactManager.Group.Group;
import com.example.ContactManager.Priority.Priority;
import com.example.ContactManager.Priority.PriorityRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @SequenceGenerator(
            name="contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    private Long id;
    private String name;
    private String phone;
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "contacts")
    private Set<Group> groups = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    public Contact() {
    }

    public Contact(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Group> getGroups(){
        return groups;
    }

    public void assignPriority(Priority priority){
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

