package com.music.music.models;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "comentarii", schema = "student")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_c")
    @SequenceGenerator(name = "seq_c", sequenceName = "seq_c", allocationSize = 1)
    int id;
    @Email
    @Column(name = "email")
    String email;
    @Column(name = "id_utilizator")
    int idUtilizator;
    @Column(name = "comentariu")
    String comentariu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }
}
