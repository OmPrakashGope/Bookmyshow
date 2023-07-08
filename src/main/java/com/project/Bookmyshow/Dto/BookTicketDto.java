package com.project.Bookmyshow.Dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Bookmyshow.Module.ShowSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTicketDto {
    private int showId;
    private String userEmail;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date bookingDate;

    private LocalTime startingTime;
    private List<String> showSeatList;
}