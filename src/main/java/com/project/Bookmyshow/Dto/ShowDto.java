package com.project.Bookmyshow.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
    private LocalTime startingTime;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date date;
    private int theatreId;
    private int movieId;
}
