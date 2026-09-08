package com.example.project1.Controller;

import com.example.project1.Api.ApiResponse;
import com.example.project1.Mudel.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> get() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Customer customer) {
        if (check(customer.getId())) {
            return new ApiResponse("this account is already exits");
        }
        customers.add(customer);
        return new ApiResponse("add successful");
    }


    @PutMapping("/update/{index}")
    public ApiResponse update(@PathVariable int index, @RequestBody Customer customer) {
        if(vldit(index,customer)) {
            return new ApiResponse("this account is already exits");
        }
        customers.set(index, customer);
        return new ApiResponse("Update successful");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse delete(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("Remove successful");
    }


    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index, @RequestBody int amount) {
        customers.get(index).setBalance(customers.get(index).getBalance() + amount);
        return new ApiResponse("Deposit successful " + customers.get(index).getBalance());
    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index, @PathVariable int amount) {
        if (amount <= customers.get(index).getBalance()) {
            customers.get(index).setBalance(customers.get(index).getBalance() - amount);
            return new ApiResponse("withdraw :" + amount + '\n' + "Remaining :" + customers.get(index).getBalance());
        }
        return new ApiResponse("Oops! Your balance is not enough for this withdrawal.");
    }



    //for add
    public boolean check(String id) {
        for (Customer cus : customers) {
            if (cus.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }





    public boolean vldit( int index ,  Customer customer){
        for(int i =0;i<customers.size();i++){
            if (i!=index&&customers.get(i).getId().equals(customer.getId())){
                return true;
            }
        }
        return false;
    }

}

