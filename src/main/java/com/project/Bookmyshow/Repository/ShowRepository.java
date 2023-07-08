package com.project.Bookmyshow.Repository;
import com.project.Bookmyshow.Module.Show;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
//    public Optional<Show> findByDate(Date date);
}
