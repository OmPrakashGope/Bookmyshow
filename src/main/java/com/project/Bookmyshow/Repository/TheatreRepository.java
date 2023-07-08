package com.project.Bookmyshow.Repository;
import com.project.Bookmyshow.Module.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
    public Optional<Theatre> findByLocation(String location);
}
