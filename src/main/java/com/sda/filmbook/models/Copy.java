package com.sda.filmbook.models;

import javax.persistence.*;

@Entity(name = "copies")
public class Copy {

    @Id
    @GeneratedValue
    private Long copyId;

    @ManyToOne
    private Movie movie;

    public Copy() {
    }

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
