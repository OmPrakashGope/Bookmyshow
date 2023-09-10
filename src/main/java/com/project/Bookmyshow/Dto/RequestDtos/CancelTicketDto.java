package com.project.Bookmyshow.Dto.RequestDtos;

import lombok.Data;

@Data
public class CancelTicketDto {
    private int TicketId;
    private int userId;
}
