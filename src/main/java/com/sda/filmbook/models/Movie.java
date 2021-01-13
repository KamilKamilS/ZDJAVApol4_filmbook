package com.sda.filmbook.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String description;
    private Genre genre;
    private LocalDate yearOfProduction;

    @OneToMany(mappedBy = "movie")
    private List<Rate> rates;

    @OneToMany(mappedBy = "movie")
    private List<Copy> copies;
}
