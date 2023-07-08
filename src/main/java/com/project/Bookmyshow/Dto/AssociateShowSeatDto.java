package com.project.Bookmyshow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociateShowSeatDto {
    private int showId;
    private int priceForClassicSeats;
    private int priceForPremiumSeats;
}
