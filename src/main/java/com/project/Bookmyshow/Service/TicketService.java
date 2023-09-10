package com.project.Bookmyshow.Service;

import com.project.Bookmyshow.Dto.RequestDtos.BookTicketDto;
import com.project.Bookmyshow.Dto.RequestDtos.CancelTicketDto;
import com.project.Bookmyshow.Dto.ResponseDto.TicketResponseDto;
import com.project.Bookmyshow.ExceptionHandling.ShowNotFoundException;
import com.project.Bookmyshow.ExceptionHandling.TicketNotFoundException;
import com.project.Bookmyshow.ExceptionHandling.UserNotFoundException;
import com.project.Bookmyshow.Repository.ShowRepository;
import com.project.Bookmyshow.Repository.TicketRepository;
import com.project.Bookmyshow.Repository.UserRepository;
import com.project.Bookmyshow.Transformer.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Bookmyshow.Module.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public TicketResponseDto bookTicket(BookTicketDto bookTicketDto) throws UserNotFoundException,ShowNotFoundException {
//        User user = userRepository.findByEmail(bookTicketDto.getUserEmail());
        System.out.println(bookTicketDto.getUserId());
        Optional<User> userOptional = userRepository.findById(bookTicketDto.getUserId());
        System.out.println(userOptional.isEmpty());
        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User of given id does not exists in the list");
        }
        Optional<Show> showOptional = showRepository.findById(bookTicketDto.getShowId());
        if(showOptional.isEmpty())
        {
            throw new ShowNotFoundException("show of given name does not exist in the list");
        }
        User user = userOptional.get();
        Show show = showOptional.get();
        Optional<List<ShowSeat>> showSeatOptional = showSeatsAvailable(bookTicketDto.getShowSeatList(),bookTicketDto.getShowId());
        List<ShowSeat> showSeatList = showSeatOptional.get();
        for(ShowSeat showSeat : showSeatList)
        {
            showSeat.setIsAvailable(false);
        }
        double price = totalPriceOfTickets(showSeatList);
        Ticket ticket = TicketTransformer.ticketDtoToTicket(bookTicketDto,price);
        ticket.setUser(user);
        ticket.setShow(show);
        System.out.println("hello");
        ticket = ticketRepository.save(ticket);
        System.out.println("hello again");
        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);
        showRepository.save(show);
        userRepository.save(user);
        TicketResponseDto ticketResponseDto = TicketTransformer.ticketToTicketDto(ticket);
        return ticketResponseDto;
    }

    private double totalPriceOfTickets(List<ShowSeat> showSeatList) {
        double price = 0;
        for(ShowSeat showSeat : showSeatList)
        {
            price += showSeat.getPrice();
        }
        return price;
    }

    private Optional<List<ShowSeat>> showSeatsAvailable(List<String> showSeatList,int showId) {
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> showSeatList1 = show.getShowSeatList();
        List<ShowSeat> showSeatAns = new ArrayList<>();
        for(String particularSeat : showSeatList) {
            boolean found = false;
            for (ShowSeat showSeat : showSeatList1) {
                if (particularSeat.equals(showSeat.getSeatNumber()) && showSeat.getIsAvailable()) {
                    showSeatAns.add(showSeat);
                    found = true;
                }
            }
            if (found == false) return Optional.empty();
        }
        return Optional.of(showSeatAns);
    }


    public void cancelTicket(CancelTicketDto cancelTicketDto) throws UserNotFoundException,TicketNotFoundException,ShowNotFoundException {
        Optional<User> userOptional = userRepository.findById(cancelTicketDto.getUserId());
        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User by given userId does not exists in the list");
        }
        User user = userOptional.get();
        Optional<Ticket> ticketOptional = ticketRepository.findById(cancelTicketDto.getTicketId());
        if(ticketOptional.isEmpty())
        {
            throw  new TicketNotFoundException("Ticket of given id is not present in the list");
        }
        Ticket ticket = ticketOptional.get();
        Show show = ticket.getShow();
        if(show == null)
        {
            throw new ShowNotFoundException("show already over for given ticket");
        }
        List<String> bookedSeatList = ticket.getBookedSeat();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(String showSeatNumber : bookedSeatList)
        {

            for(ShowSeat showSeat : showSeatList)
            {
                if(showSeat.getSeatNumber().equals(showSeatNumber))
                {
                    showSeat.setIsAvailable(true);
                }
            }
        }
        ticketRepository.delete(ticket);
    }


    public List<TicketResponseDto> getAllTicketsOfUser(String userEmailId) throws UserNotFoundException{
        User user = userRepository.findByEmail(userEmailId);
        if(user == null)
        {
            throw new UserNotFoundException("User of given name does not exists in the list");
        }
        List<Ticket> ticketList = user.getTicketList();
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for(Ticket ticket : ticketList)
        {
            TicketResponseDto ticketResponseDto = TicketTransformer.ticketToTicketDto(ticket);
            ticketResponseDtoList.add(ticketResponseDto);
        }
        return ticketResponseDtoList;
    }
}
