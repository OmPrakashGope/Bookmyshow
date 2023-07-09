package com.project.Bookmyshow.Transformer;

import com.project.Bookmyshow.Dto.BookTicketDto;
import com.project.Bookmyshow.Dto.TicketResponseDto;
import com.project.Bookmyshow.Module.ShowSeat;
import com.project.Bookmyshow.Module.Ticket;

import java.util.List;

public class TicketTransformer {
    public static TicketResponseDto ticketToTicketDto(Ticket ticket)
    {
        TicketResponseDto ticketResponseDto =TicketResponseDto.builder().price(ticket.getTotalTicketPrice()).
            showDate(ticket.getMovieDate()).showTiming(ticket.getStartingTime()).showSeat(ticket.getBookedSeat()).build();
        return ticketResponseDto;
    }
    public static Ticket ticketDtoToTicket(BookTicketDto bookTicketDto, double price)
    {
        Ticket ticket = Ticket.builder().totalTicketPrice(price).bookedSeat(bookTicketDto.getShowSeatList()).
                movieDate(bookTicketDto.getBookingDate()).startingTime(bookTicketDto.getStartingTime()).build();
        return ticket;
    }
}
