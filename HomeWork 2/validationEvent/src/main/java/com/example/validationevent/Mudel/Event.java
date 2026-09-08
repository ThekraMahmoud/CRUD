package com.example.validationevent.Mudel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Event {

    @NotEmpty(message = "You forgot the ID. Can you complete it, please?")
    @Size(min = 3, max = 10, message = "Oops! Your ID must be between 3 and 10 characters.")
    private String id;

    @NotEmpty(message = "Oops! You need to write a description.")
    @Size(min = 16,max = 60,message = "Description must be between 16 and 60 characters.")
    private String description;


    @NotNull(message = "plz Enter capacity number !")
    @Min(value = 26 ,message = "Oops! Capacity must be more than 25 .")
    private Integer capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")


    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")


    private LocalDate endDate;

}