package com.music.music.models;

import javax.persistence.*;

@Entity
@Table(name = "voturi", schema = "student")
public class Vote {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_v")
    @SequenceGenerator(name = "seq_v", sequenceName = "seq_v", allocationSize = 1)
    int id;
    @Column(name = "id_melodie")
    int idMelodie;
    @Column(name = "id_user")
    int idUtilizator;

    public Vote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMelodie() {
        return idMelodie;
    }

    public void setIdMelodie(int idMelodie) {
        this.idMelodie = idMelodie;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public Vote(int idMelodie, int idUtilizator) {
        this.idMelodie = idMelodie;
        this.idUtilizator = idUtilizator;
    }
}
