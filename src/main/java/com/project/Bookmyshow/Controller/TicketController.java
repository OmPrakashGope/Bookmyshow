package com.project.Bookmyshow.Controller;

import com.project.Bookmyshow.Dto.BookTicketDto;
import com.project.Bookmyshow.Dto.TicketResponseDto;
import com.project.Bookmyshow.Repository.ShowRepository;
import com.project.Bookmyshow.Repository.TicketRepository;
import com.project.Bookmyshow.Repository.UserRepository;
import com.project.Bookmyshow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/get-ticket")
    public ResponseEntity<?> bookTicket(BookTicketDto bookTicketDto) {
        try {
           TicketResponseDto ticketResponseDto = ticketService.bookTicket(bookTicketDto);
           return new ResponseEntity<>(ticketResponseDto, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
