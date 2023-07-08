package com.project.Bookmyshow.Module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Bookmyshow.Enum.Genre;
import com.project.Bookmyshow.Enum.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import com.project.Bookmyshow.Enum.*;
import java.util.*;

@Entity
@Table(name = "Movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String movieName;
    private LocalTime duration;
    @JsonFormat(pattern ="MM/dd/yyyy")
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Language language;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();

}
