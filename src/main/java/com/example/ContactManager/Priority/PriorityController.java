package com.example.ContactManager.Priority;


import com.example.ContactManager.Group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "priority")
public class PriorityController {
    private final PriorityService priorityService;

    @Autowired
    public PriorityController(PriorityService priorityService){
        this.priorityService = priorityService;
    }

    @GetMapping
    public List<Priority> getPriority(){
        return priorityService.getPriority();
    }

    @GetMapping("/{priorityId}")
    public Priority getPriorityById(@PathVariable("priorityId") Long priorityId) {
        return priorityService.getPriorityById(priorityId);

    }

    @PostMapping
    public void createNewPriority(@RequestBody Priority priority){
        priorityService.addNewPriority(priority);
    }

    @DeleteMapping(path = "{priorityId}")
    public void deletePriority(@PathVariable("priorityId") Long priorityId){
        priorityService.deletePriority(priorityId);
    }

    @PutMapping("/{priorityId}")
    public ResponseEntity<Priority> updatePriority(
            @PathVariable("priorityId") Long priorityId,
            @RequestBody Priority priority) {
        Priority updatedPriority = priorityService.updatePriority(priorityId, priority);
        return ResponseEntity.ok(updatedPriority);
    }
}
