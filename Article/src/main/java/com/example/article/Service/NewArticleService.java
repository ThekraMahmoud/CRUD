package com.example.article.Service;

import com.example.article.Mudel.NewArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewArticleService {



    ArrayList<NewArticle>newArticles=new ArrayList<>();



    public ArrayList<NewArticle> getNewArticles(){
        return newArticles;
    }


    public boolean addN(NewArticle article){
            newArticles.add(article);
            return true;
    }



    public boolean update (String id, NewArticle newArticle){

        for(int i =0 ;i<newArticles.size();i++) {
            if (newArticles.get(i).getId().equals(id)) {
                newArticles.set(i,newArticle);
                return true;
            }
        }

     return false  ;
    }


    public boolean delete(String id){
        for(NewArticle newArticle:newArticles){
            if(newArticle.getId().equals(id)){
                newArticles.remove(newArticle);
                return true;
            }
        }
        return false;
    }




    public String cheakIsPublish(String id) {

        for (NewArticle newArticle : newArticles) {
            if (newArticle.getId().equals(id)) {
                  if(newArticle.isPublished()) {
                  return "already";
            }
                  newArticle.setPublished(true);
                  return "success";
                }
            }

        return "not found";

        }





        public ArrayList<NewArticle>gutPublish(){
        ArrayList<NewArticle>publish=new ArrayList<>();
        for(NewArticle n:newArticles){
            if(n.isPublished()){
                publish.add(n);
            }
        }
        return publish;
        }




        public ArrayList<NewArticle>research(String category){
        ArrayList<NewArticle>newA=new ArrayList<>();
        for(NewArticle n:newArticles){
            if (n.getCategory().equals(category)){
                newA.add(n);
            }
            if(newA.isEmpty()){
                return null;
            }
        } return newA;
        }
    }



