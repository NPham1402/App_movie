package com.example.app_movie;

<<<<<<< HEAD
import java.io.Serializable;
=======
<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> 1b44f0ae57aff27d72da10e3611ed8ae508826bd
>>>>>>> a3d75f1ff628560243359a09491050c20d284a18
import java.sql.Array;
import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class FilmClass implements Serializable {
=======
<<<<<<< HEAD
public class FilmClass {
=======
public class FilmClass implements Serializable {
>>>>>>> 1b44f0ae57aff27d72da10e3611ed8ae508826bd
>>>>>>> a3d75f1ff628560243359a09491050c20d284a18
    public String name;
    private String url_img;
    private String urlfilm;
    public List<String> actor;
    public List<String> director;
    public String description;
    public String document;
    public List<String> genre;
    public FilmClass(){

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public FilmClass(List<String>director, List<String> genre, String name, String description, String url_img)
    {
        this.description=description;
        this.director=director;
        this.genre=genre;
        this.name=name;
        this.url_img=url_img;
    }

}
