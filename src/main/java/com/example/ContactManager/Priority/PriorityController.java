package com.example.ContactManager.Priority;


import com.example.ContactManager.Group.Group;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping(path = "{priorityId}")
    public void updatePriority(
            @PathVariable("priorityId") Long priorityId,
            @RequestParam(required = false) String name){
        priorityService.updatePriority(priorityId, name);
    }
}
