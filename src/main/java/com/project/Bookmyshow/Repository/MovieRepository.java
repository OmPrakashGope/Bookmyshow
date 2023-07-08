package com.project.Bookmyshow.Repository;
import com.project.Bookmyshow.Module.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
