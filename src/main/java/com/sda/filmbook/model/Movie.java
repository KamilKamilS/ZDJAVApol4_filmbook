package com.sda.filmbook.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String description;
    private Genre genre;
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Rate> rates = new ArrayList<>();

    @OneToMany(mappedBy = "movie",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Copy> copies = new ArrayList<>();
}
