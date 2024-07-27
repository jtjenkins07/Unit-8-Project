package com.example.ContactManager.Priority;

import com.example.ContactManager.Contact.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority {
    @Id
    @SequenceGenerator(
            name="priority_sequence",
            sequenceName = "priority_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "priority_sequence"
    )
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "priority")
    private Set<Contact> contacts = new HashSet<>();

    public Priority() {
    }

    public Priority(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Priority(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
