package com.example.article.Mudel;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewArticle {


    @NotEmpty(message = "Please Enter id ")
    private String id;

    @NotEmpty(message = "please Enter title")
    @Size(max=100,message = "Size more lass thin 100")
    private String title;

    @NotEmpty(message = "Please Enter The author")
    @Size(min = 5,max = 20)
    private String author;



    @NotEmpty(message = "Enter the category please")
    @Pattern(regexp = ("^(politics|sports|technology)"),message = (" Category most one of these politics or sports or technology"))
    private String category;


    @NotEmpty(message = "Please Enter the content ")
    @Size(min = 200,message = "Most be up for 200")
    private String content;

    @NotEmpty(message = "Please Enter image Url")
    private String imageUrl;


    @AssertFalse(message = "isPublished most be false")
    private boolean isPublished;


    @JsonFormat(pattern = ("yyyy-MM-dd"))
    private LocalDate publishDate;


}
