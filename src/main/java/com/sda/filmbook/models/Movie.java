package com.sda.filmbook.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name= "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String description;
    private Genre genre;
    private LocalDate yearOfProduction;

    @OneToMany
    @JoinColumn(name = "rateId")
    private List<Rate> rates;

    @OneToMany
    @JoinColumn(name = "copyId")
    private List<Copy> copies;
}
