package com.example.employeemangament.Controller;


import com.example.employeemangament.Api.ApiResponse;
import com.example.employeemangament.Mudel.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    ArrayList<Employee>employees=new ArrayList<>();



    @GetMapping("/get")
    public ResponseEntity<?>get(){
        return ResponseEntity.status(200).body(employees);
    }


    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody @Valid Employee employee , Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(employee.isOnLeave()){
            return ResponseEntity.status(400).body(new ApiResponse("New employee cannot be on leave"));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("add successfully"));
    }


@PutMapping("/update/{index}")
    public ResponseEntity<?>update(@PathVariable int index ,@RequestBody @Valid Employee employee,Errors errors){

        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if (index<0||index>=employees.size()){
            return ResponseEntity.status(400).body(new ApiResponse("The index out of the bound"));
        }


        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("Updating successfully"));
    }

@DeleteMapping("/delete/{index}")
    public ResponseEntity<?>delete(@PathVariable int index){
        if (index<0||index>=employees.size()){
            return ResponseEntity.status(400).body(new ApiResponse("index out of the bound"));
        }

        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Remove  successfully"));
    }


@GetMapping("/search/{position}")
    public ResponseEntity<?>search(@PathVariable String position){
        ArrayList<Employee>position1=new ArrayList<>();
        for(Employee employee:employees){
            if (employee.getPosition().equalsIgnoreCase(position)){
                position1 .add(employee);
            }
        }

        if(position1.isEmpty()) {
        return ResponseEntity.status(400).body(new ApiResponse(" Sorry Not Fond "));
    }
    return ResponseEntity.status(200).body(position1);
}


    @GetMapping("/check/{minAge}/{maxAge}")
    public ResponseEntity<?>chekAge(@PathVariable int minAge,@PathVariable int maxAge) {
        ArrayList<Employee> ages = new ArrayList<>();
        for (Employee employee : employees) {
            int age = employee.getAge();
            if (age >= minAge && age <= maxAge) {
                ages.add(employee);
            }
        }
        if (ages.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("Not have age between the range"));
        }

        return ResponseEntity.status(200).body(ages);
    }



    @PutMapping("/leaveRequest/{index}")
    public ResponseEntity<?>leaveRequest(@PathVariable int index) {
        if (index < 0 || index >= employees.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("index out of the bound"));
        }
        if (employees.get(index).getAnnualLeave() >= 1) {
            if (employees.get(index).isOnLeave()) {
                return ResponseEntity.status(400).body(new ApiResponse("You already have holiday"));
            }
            employees.get(index).setOnLeave(true);
            employees.get(index).setAnnualLeave(employees.get(index).getAnnualLeave() - 1);
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("You don't have enough annual leave"));

        }
        return ResponseEntity.status(200).body(new ApiResponse("Happy holiday : "+employees.get(index).getName()+" ,new you have :"+employees.get(index).getAnnualLeave()+" Days of Leave"));
    }

    @GetMapping("/chack-holiday")
    public ResponseEntity<?>notHaveLeave(){
        ArrayList<Employee>notLeave=new ArrayList<>();
        for(Employee employee:employees){
            int leave=employee.getAnnualLeave();
             if (leave==0){
                 notLeave.add(employee);
            }
        } if(notLeave.isEmpty()){
                return ResponseEntity.status(400).body(new ApiResponse("Not have employ Without Holiday "));
        }
        return ResponseEntity.status(200).body(notLeave);
    }



  @PutMapping("/promotion/{SupIndex}/{coordinatorIndex}")
    public ResponseEntity<?>promotion(@PathVariable int SupIndex,@PathVariable int coordinatorIndex ) {
        if (SupIndex < 0 || SupIndex >= employees.size()||coordinatorIndex<0||coordinatorIndex>=employees.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("index out of the bound"));
        }
        if (employees.get(SupIndex).getPosition().equalsIgnoreCase("Supervisor")) {
            if (employees.get(coordinatorIndex).getPosition().equalsIgnoreCase("coordinator")) {
                if (employees.get(coordinatorIndex).getAge() > 29) {
                    if (!employees.get(coordinatorIndex).isOnLeave()) {
                        employees.get(coordinatorIndex).setPosition("Supervisor");
                    } else {
                        return ResponseEntity.status(400).body(new ApiResponse("the Employee have Leave "));
                    }
                } else {
                    return ResponseEntity.status(400).body(new ApiResponse("oh , the age of : " + employees.get(coordinatorIndex).getName() + " , lass thin 30"));
                }
            } else {
                return ResponseEntity.status(400).body(new ApiResponse("A Supervisor @_@"));
            }
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("oh Yor Not Supervisor "));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Congratulation"));
    }
}





