package com.sda.filmbook.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;
    private int rate;
    private String description;

    //TODO - rate service
}
