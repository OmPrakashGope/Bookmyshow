package com.project.Bookmyshow.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDto {
    private List<String> showSeat;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date showDate;
    private LocalTime showTiming;
    private double price;
}
