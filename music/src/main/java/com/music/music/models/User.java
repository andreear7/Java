package com.music.music.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "utilizatori", schema = "student")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_u")
    @SequenceGenerator(name = "seq_u", sequenceName = "seq_u", allocationSize = 1)
    int id;
    @Column(name = "nume")
    @NotNull(message = "Numele este obligatoriu!")
    String nume;
    @Column(name = "parola")
    @NotNull(message = "Parola este obligatorie!")
    String parola;

    public User(String nume, String parola) {
        this.nume=nume;
        this.parola=parola;
    }

    public User(int id, @NotBlank(message = "Numele este obligatoriu!") String nume, @NotBlank(message = "Parola este obligatorie!") String parola) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


}
