package com.project.Bookmyshow.Controller;

import com.project.Bookmyshow.Dto.RequestDtos.BookTicketDto;
import com.project.Bookmyshow.Dto.RequestDtos.CancelTicketDto;
import com.project.Bookmyshow.Dto.ResponseDto.TicketResponseDto;
import com.project.Bookmyshow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/book-ticket")
    public ResponseEntity<?> bookTicket(@RequestBody BookTicketDto bookTicketDto) {
        try {
           TicketResponseDto ticketResponseDto = ticketService.bookTicket(bookTicketDto);
           return new ResponseEntity<>(ticketResponseDto, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestBody CancelTicketDto cancelTicketDto)
    {
        try {
            ticketService.cancelTicket(cancelTicketDto);
            return new ResponseEntity<>("Ticket cancelled for given data",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-ticket-user")
    public ResponseEntity<?> getAllTicketsOfUser(@RequestParam String userEmailId)
    {
        try {
            List<TicketResponseDto> ticketResponseDtoList = ticketService.getAllTicketsOfUser(userEmailId);
            return new ResponseEntity<>(ticketResponseDtoList,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
