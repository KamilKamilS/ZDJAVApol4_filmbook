package com.sda.filmbook.repository;

import com.sda.filmbook.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
