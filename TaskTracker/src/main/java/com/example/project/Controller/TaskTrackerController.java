package com.example.project.Controller;


import com.example.project.Api.ApiResponse;
import com.example.project.Mudel.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")

public class TaskTrackerController {

    ArrayList<Task>tasks=new ArrayList<>();



    //Display
    @GetMapping("/get")
    public ArrayList<Task> get(){
        return tasks;
    }


    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        if(check(task)) {
            return new ApiResponse(" it already exists.");
        }
        tasks.add(task);
        return new ApiResponse("Add successful");
    }

//Delete
    @DeleteMapping("/delete/{index}")
    public ApiResponse delete(@PathVariable int index){
     tasks.remove(index);
     return new ApiResponse("Deleted successful");
    }



//Update
    @PutMapping("update/{index}")
    public ApiResponse updateTask(@PathVariable int index,@RequestBody Task task){
        if(verify(task,index)){
            return new ApiResponse("it already exists.");
        }
        tasks.set(index,task);
        return new ApiResponse("Updating ");
    }


//Done or not
    @PutMapping("update/status/{index}")
    public ApiResponse status(@PathVariable int index, @RequestBody String status) {
        status = status.replace("\"", "");

        if (status.equalsIgnoreCase(("Done")) || status.equalsIgnoreCase("Not Done")) {
            tasks.get(index).setStatus(status);
            return new ApiResponse("good");
        }
        return new ApiResponse("Status Not Update Sorry ^_^");
    }


//Search for title use ArrayList
           @GetMapping("search/{title}")
            public ArrayList<Task> task (@PathVariable String title) {
               ArrayList<Task> taskTitle = new ArrayList<>();
               for (Task task : tasks) {
                   if (task.getTitle().equalsIgnoreCase(title)) {
                       taskTitle.add(task);
                   }
               }
               return taskTitle;
           }


//Search for id
    @GetMapping("searchId/{id}")
    public Task searchId(@PathVariable String id){
       for(Task task:tasks) {
           if (task.getId().equalsIgnoreCase(id)){
               return task;
           }
       }
       return null;
    }


//Verify method for add
    public boolean check(Task task){
        for (Task taskId:tasks) {
            if (taskId.getId().equals(task.getId())){
                return true;
            }
        }
        return false;
    }

    //Verify method for update

    public boolean verify(Task task ,int index){
        for(int i=0;i<tasks.size();i++){
            if (i!=index&&tasks.get(i).getId().equals(task.getId())){
                return true;}

        }

        return false;
}


}
