package com.project.Bookmyshow.Repository;
import com.project.Bookmyshow.Module.*;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
