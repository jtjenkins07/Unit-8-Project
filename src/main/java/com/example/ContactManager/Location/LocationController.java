package com.example.ContactManager.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getLocation(){
        return locationService.getLocation();
    }

    @PostMapping
    public void createNewLocation(@RequestBody Location location){
        locationService.addNewLocation(location);
    }

    @DeleteMapping
    public void deleteLocation(@PathVariable("locationId") Long locationId){
        locationService.deleteLocation(locationId);
    }

    @PutMapping
    public void updateLocation(
            @PathVariable("locationId") Long locationId,
            @RequestParam(required = false) String name){
        locationService.updateLocation(locationId, name);
    }

    @PutMapping("/{locationId}/contact/{contactId}")
    Location assignLocationToContact(
            @PathVariable Long locationId,
            @PathVariable Long contactId){
        locationService.assignLocationToContact(locationId, contactId);
        return null;
    }
}
