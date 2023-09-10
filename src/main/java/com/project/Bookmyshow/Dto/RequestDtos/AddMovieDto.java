package com.project.Bookmyshow.Dto.RequestDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Bookmyshow.Enum.Genre;
import com.project.Bookmyshow.Enum.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMovieDto {
    private String movieName;
    private LocalTime duration;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
