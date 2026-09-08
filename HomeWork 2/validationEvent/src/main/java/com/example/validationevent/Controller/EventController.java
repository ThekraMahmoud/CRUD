package com.example.validationevent.Controller;

import com.example.validationevent.Api.ApiResponse;
import com.example.validationevent.Mudel.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {


    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        for(Event ev:events){
            if(ev.getId().equals(event.getId())){
                return ResponseEntity.status(400).body(new ApiResponse("This ID is already used."));
            }
        }

        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Add successfully"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity<?> update(@PathVariable int index, @RequestBody @Valid Event event, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("!! You are out of index. Index must be between 0 and " + (events.size() - 1)));
        }

        for(int i=0;i<events.size();i++){
            if(i!=index&&events.get(i).getId().equals(event.getId())){
                return ResponseEntity.status(400).body(new ApiResponse("This ID is already used."));
                }
        }

        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("update successfully"));
    }


    @PutMapping("/capacity/{index}/{capacity}")
    public ResponseEntity<?> capacity(@PathVariable int index, @PathVariable  int capacity ) {
         if(capacity<=25) {
             return ResponseEntity.status(400).body(new ApiResponse("Oops! Capacity must be more than 25 "));
        }
        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("!! You are out of index. Index must be between 0 and " + (events.size() - 1)));
        }
     events.get(index).setCapacity(capacity);
    return ResponseEntity.status(200).body(new ApiResponse("Capacity updated successfully"));
    }



    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?>delete(@PathVariable int index){

        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("!! You are out of index. Index must be between 0 and " + (events.size() - 1)));
        }
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Remove successfully"));
    }
}