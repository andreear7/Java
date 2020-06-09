package com.music.music.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "melodii", schema = "student")
public class Song {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_s")
    @SequenceGenerator(name = "seq_s", sequenceName = "seq_s", allocationSize = 1)
    int id;
    @Column(name = "nume")
    @NotBlank(message = "Numele este obligatoriu!")
    String numeMelodie;
    @Column(name = "autor")
    @NotBlank(message = "Autorul este obligatoriu!")
    String autor;
    @Column(name = "descriere")
    String descriere;
    @Column(name = "link_melodie")
    String linkMelodie;
    @Column(name = "gen")
    String gen;
    @Column(name = "voturi")
    int voturi;
    @Column(name = "id_utilizator")
    int idUtilizator;

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeMelodie() {
        return numeMelodie;
    }

    public void setNumeMelodie(String numeMelodie) {
        this.numeMelodie = numeMelodie;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getLinkMelodie() {
        return linkMelodie;
    }

    public void setLinkMelodie(String linkMelodie) {
        this.linkMelodie = linkMelodie;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public int getVoturi() {
        return voturi;
    }

    public void setVoturi(int voturi) {
        this.voturi = voturi;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", numeMelodie='" + numeMelodie + '\'' +
                ", autor='" + autor + '\'' +
                ", descriere='" + descriere + '\'' +
                ", linkMelodie='" + linkMelodie + '\'' +
                ", gen='" + gen + '\'' +
                ", voturi=" + voturi +
                '}';
    }
}
