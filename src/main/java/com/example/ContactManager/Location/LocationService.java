package com.example.ContactManager.Location;


import com.example.ContactManager.Contact.Contact;
import com.example.ContactManager.Contact.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @Autowired
    ContactRepository contactRepository;

    public List<Location> getLocation(){
        return locationRepository.findAll();
    }

    public void addNewLocation(Location location){
        locationRepository.save(location);
    }

    public void deleteLocation(Long locationId){
        boolean exists = locationRepository.existsById(locationId);
        if (!exists){
            throw new IllegalStateException("Location ID: " + locationId + "does not exist.");
        }
        locationRepository.deleteById(locationId);
    }

    @Transactional
    public void updateLocation(Long locationId, String address){
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new IllegalStateException("Location ID: " + locationId + "does not exist."));
        if (address != null && !address.isEmpty() && !Objects.equals(location.getAddress(), address)){
            location.setAddress(address);
        }
    }

    public void assignLocationToContact(Long locationId, Long contactId){
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new IllegalStateException("Location ID: " + locationId + "does not exist."));
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new IllegalStateException("Contact ID: " + contactId + "does not exist."));
        location.assignContact(contact);
        locationRepository.save(location);
    }
}
