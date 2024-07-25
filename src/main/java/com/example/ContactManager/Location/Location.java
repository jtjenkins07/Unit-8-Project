package com.example.ContactManager.Location;

import com.example.ContactManager.Contact.Contact;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String zipCode;
    private String country;

    @OneToMany(mappedBy = "location")
    private Set<Contact> contacts = new HashSet<>();

    public Location() {
    }

    public Location(Long id, String address, String city, String zipCode, String country, Set<Contact> contacts) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.contacts = contacts;
    }

    public Location(String address, String city, String zipCode, String country, Set<Contact> contacts) {
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Set<Contact> getContact(){
        return contacts;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
