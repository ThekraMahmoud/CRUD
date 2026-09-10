package com.example.article.Cotroller;

import com.example.article.Api.ApiRespons;
import com.example.article.Mudel.NewArticle;
import com.example.article.Service.NewArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/newAricle")
@RequiredArgsConstructor
public class NewArticleController {

    private final NewArticleService articleService;


    @GetMapping("/get")
    public ResponseEntity<?>get(){
        ArrayList<NewArticle>newArticles=articleService.getNewArticles();
        return ResponseEntity.status(200).body(newArticles);
    }


    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody @Valid NewArticle newArticle, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean chack=articleService.addN(newArticle);
       if(chack) {
         return ResponseEntity.status(200).body("Add successfully");
      }
       return ResponseEntity.status(500).body("the isPublished in add most be false");
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable String id , @RequestBody @Valid NewArticle newArticle,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean update=articleService.update(id,newArticle);
        if(!update){
            return ResponseEntity.status(400).body("id not found ");
        }
        return ResponseEntity.status(200).body("Update successfully");
    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        boolean delete1=articleService.delete(id);
        if(delete1){
            return ResponseEntity.status(200).body("remove successfully");
        }
        return ResponseEntity.status(400).body("not found");
    }


    @PutMapping("/publish/{id}")
    public ResponseEntity<?>publishUpdate(@PathVariable String id ){
        String result=articleService.cheakIsPublish(id);
        if (result.equals("already")) {
            return ResponseEntity.status(400).body(" is already Publish ");
        }
        if(result.equals("success")) {
            return ResponseEntity.status(200).body("Publish successfully");
        }
        return ResponseEntity.status(400)
                .body(new ApiRespons("Article not found"));
    }


    @GetMapping("/getPublish")
    public ResponseEntity<?>getPublish(){
        ArrayList<NewArticle>getPublish=articleService.gutPublish();
        return ResponseEntity.status(200).body(getPublish);
    }



@GetMapping("/getCategory/{category}")
    public ResponseEntity<?>getCategory(@PathVariable String category){
       ArrayList<NewArticle>getCategory=articleService.research(category);

        if(getCategory.isEmpty()){
           return ResponseEntity.status(400).body("Category not found");
        }
       return ResponseEntity.status(200).body(getCategory);
    }
}
