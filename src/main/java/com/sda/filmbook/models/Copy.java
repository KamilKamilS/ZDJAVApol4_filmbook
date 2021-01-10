package com.sda.filmbook.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "copies")
public class Copy {

    @Id
    @GeneratedValue
    private Long copy_id;

    private Movie movie;

}
