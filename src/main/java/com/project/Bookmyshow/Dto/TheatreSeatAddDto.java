package com.project.Bookmyshow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreSeatAddDto {
    private String location;
    private int numberOfSeatsInOneRow;
    private int classicSeats;
    private int premiumSeats;
}
