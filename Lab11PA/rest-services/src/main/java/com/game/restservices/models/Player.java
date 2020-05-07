package com.game.restservices.models;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;


 @Entity
    @Document(collection = "Player")
    public class Player {
        @Id
        private String id;
        private String name;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
 }