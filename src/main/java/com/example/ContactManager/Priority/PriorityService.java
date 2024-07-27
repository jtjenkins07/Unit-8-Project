package com.example.ContactManager.Priority;

import com.example.ContactManager.Contact.Contact;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityService(PriorityRepository priorityRepository){
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> getPriority(){
        return priorityRepository.findAll();
    }

    public Priority getPriorityById(Long priorityId){
        Optional<Priority> priority = priorityRepository.findById(priorityId);
        return priority.orElseThrow(() -> new IllegalStateException("Priority ID: " + priorityId + " could not be found."));
    }

    public void addNewPriority(Priority priority){
        priorityRepository.save(priority);
    }

    public void deletePriority(Long priorityId){
        boolean exists = priorityRepository.existsById(priorityId);
        if (!exists){
            throw new IllegalStateException("Priority ID: " + priorityId + "does not exist.");
        }
        priorityRepository.deleteById(priorityId);
    }

    @Transactional
    public void updatePriority(Long priorityId, String name){
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new IllegalStateException("Priority ID: " + priorityId + "does not exist."));
    }
}
