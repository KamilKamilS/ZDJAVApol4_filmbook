package com.sda.filmbook.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity(name= "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;

    private String title;
    private String description;
    private Genre genre;
    private int yearOfProduction;
    private List<Rate> rates;
    private List<Copy> copies;
}
