package com.sda.filmbook.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private PriceIndex priceIndex;

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

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
