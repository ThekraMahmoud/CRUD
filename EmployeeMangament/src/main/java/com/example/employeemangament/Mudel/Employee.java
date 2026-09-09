package com.example.employeemangament.Mudel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;


import jakarta.validation.constraints.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Employee {

    @NotEmpty(message = "need id ")
    @Size(min = 3,message = "The id length most be greater thin 2")
    private String id;

    @NotEmpty(message = "need Name")
    @Size(min = 5,message ="The name Length most be greater then 4" )
    @Pattern(regexp = ("^[a-zA-Z]+$"),message = "The name most be character only")
    private String name;


    @Email(message = "i think You Forget @ or email")
    private  String email;


    @Pattern(regexp =("^05\\d{8}$"),message = "Phone number must start with 05")
//@Size(min = 10,max = 10)
    private String phoneNumber;

    @NotNull(message=" need Your Age !")
//@Pattern(regexp = ("^[1-9]$"),message = "Jest Allow number ")
    @Positive(message = "Age must be a positive number")
    @Min(25)
    private Integer age;

    @NotEmpty(message="plz Enter The position !")
    @Pattern(regexp=("^(Supervisor|coordinator)?"),message = "Most Match Supervisor or coordinator")
    private String position;


    private boolean onLeave;



    @NotNull(message ="most be inter Your Start Date")
    @PastOrPresent(message = "Not Allow Enter the Fuotcher")
    private LocalDate hirDate;
    @JsonFormat(pattern = "yyyy-MM-dd")



    @NotNull(message = "plz enter your Annual Leave")
    @Positive(message = "Not have Negative Annual Leave , plz Enter Positive Number ")
    private Integer annualLeave;



}
