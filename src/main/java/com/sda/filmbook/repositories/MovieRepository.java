package com.sda.filmbook.repositories;

import com.sda.filmbook.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
