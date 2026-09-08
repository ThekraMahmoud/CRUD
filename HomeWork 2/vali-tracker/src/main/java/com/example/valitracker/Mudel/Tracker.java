package com.example.valitracker.Mudel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.message.Message;

@Data
@AllArgsConstructor
public class Tracker {

    @NotEmpty(message = "You forgot the ID. Can you complete it, please?")
    @Size(min = 3, max = 10, message = "Oops! Your ID must be between 3 and 10 characters.")
    private String id;

    @NotEmpty(message = "Where is your tracker title?! Please enter it.")
    @Size(min = 9,max = 60,message = "Title must be between 9 and 60 characters.")
    private String title;


    @NotEmpty(message = "Oops! You need to write a description.")
    @Size(min = 16,max = 60,message = "Description must be between 16 and 60 characters.")
    private String Description;


    @NotEmpty(message = "Oops! I think you forgot the status @_@")
    @Pattern(regexp = "(?i)^(Not Started|In Progress|Completed)$"
            ,message = "Status Most be one of this < Not Started Or In Progress Or Completed >")
     private String status;


    @NotEmpty(message="Please Enter the Company Name !")
    @Size(min=7,max = 15,message = "Company Name must be between 7 and 60 characters.")
    private String companyName;
}
