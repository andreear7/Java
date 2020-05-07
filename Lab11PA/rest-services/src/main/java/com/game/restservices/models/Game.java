package com.game.restservices.models;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


    @Entity
    @Document(collection = "Game")
    public class Game {
        @Id
        private String id;
        private String contentOfTheGame;
        private String date;
        private String result;
      //  @ElementCollection
      //  private List<Player> players;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContentOfTheGame() {
            return contentOfTheGame;
        }

        public void setContentOfTheGame(String contentOfTheGame) {
            this.contentOfTheGame = contentOfTheGame;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

//        public List<Player> getPlayers() {
//            return players;
//        }
//
//        public void setPlayers(List<Player> players) {
//            this.players = players;
//        }


    }
