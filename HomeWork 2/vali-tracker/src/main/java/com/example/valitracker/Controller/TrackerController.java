package com.example.valitracker.Controller;

import com.example.valitracker.Api.ApiResponse;
import com.example.valitracker.Mudel.Tracker;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {

    ArrayList<Tracker>trackers=new ArrayList<>();


    @GetMapping("/get")
    public ResponseEntity<?>get(){
        return ResponseEntity.status(200).body(trackers);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody @Valid Tracker tracker, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        trackers.add(tracker);
        return ResponseEntity.status(200).body(new ApiResponse("Add successful"));
    }


     @PutMapping("/update/{index}")
      public ResponseEntity <?>update(@PathVariable int index ,@RequestBody @Valid Tracker tracker,Errors errors){
         if(errors.hasErrors()){
             String message=errors.getFieldError().getDefaultMessage();
             return ResponseEntity.status(400).body(new ApiResponse(message));
         }

        if (index<0||index>=trackers.size()){
            return ResponseEntity.status(400).body(new ApiResponse("!! You are out of index. Index must be between 0 and " + (trackers.size() - 1)));
        }

        String oldStatus=trackers.get(index).getStatus();
        String newStatus=tracker.getStatus();
         int oldOrder=oldStatus.equalsIgnoreCase("Not Started")?1:oldStatus.equalsIgnoreCase("In Progress")?2:3;
         int newOrder=newStatus.equalsIgnoreCase("Not Started")?1:newStatus.equalsIgnoreCase("In Progress")?2:3;

         // If the new status goes backward OR skips more than one step, reject it.

         if (newOrder<oldOrder||newOrder>oldOrder+1){
             return ResponseEntity.status(400).body(new ApiResponse("Invalid input! You must follow the order."));
         }
        trackers.set(index,tracker);
        return ResponseEntity.status(200).body(new ApiResponse("Update successfully"));
    }

  @PutMapping("/updateStatus/{index}")
    public ResponseEntity<?>updateStatus(@PathVariable int index){

        if(index<0||index>=trackers.size()){
            return ResponseEntity.status(400).body(new ApiResponse("!! You are out of index. Index must be between 0 and " + (trackers.size() - 1)));
        }
        Tracker currentTracker=trackers.get(index);
        String status=currentTracker.getStatus();

          if(status.equalsIgnoreCase("Not Started")){
            currentTracker.setStatus("In Progress");
            return ResponseEntity.status(200).body("Updating Status to In Progress Successfully");

        } else if(status.equalsIgnoreCase("In Progress")){
            currentTracker.setStatus("Completed");
            return ResponseEntity.status(200).body("Updating Status to Completed Successfully");

        }else
            return ResponseEntity.status(200).body("The tracker is already Completed.");

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?>delete(@PathVariable int index){
        if(index<0||index>=trackers.size()){
            return ResponseEntity.status(400).body(new ApiResponse("!! You are out of index. Index must be between 0 and " + (trackers.size() - 1)));
        }
        trackers.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Removed Successfully"));

    }






}
