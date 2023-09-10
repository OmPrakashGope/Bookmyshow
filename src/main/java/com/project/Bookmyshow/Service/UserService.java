package com.project.Bookmyshow.Service;
import com.project.Bookmyshow.Dto.ResponseDto.TicketResponseDto;
import com.project.Bookmyshow.ExceptionHandling.EmailAlreadyExistException;

import com.project.Bookmyshow.Dto.RequestDtos.UserDto;
import com.project.Bookmyshow.ExceptionHandling.UserNotFoundException;
import com.project.Bookmyshow.Module.Ticket;
import com.project.Bookmyshow.Repository.UserRepository;
import com.project.Bookmyshow.Transformer.TicketTransformer;
import com.project.Bookmyshow.Transformer.UserTransformer;
import com.project.Bookmyshow.Module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void addUser(UserDto userDto) throws EmailAlreadyExistException{
       User user = userRepository.findByEmail(userDto.getEmail());
        if(!(user == null))
        {
            throw new EmailAlreadyExistException("Email Id already exist");
        }
        user = UserTransformer.userDtoToEntity(userDto);
        userRepository.save(user);
    }

    public List<TicketResponseDto> allTickets(Integer userId) throws UserNotFoundException {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User user = userOpt.get();
        List<Ticket> ticketList = user.getTicketList();
        List<TicketResponseDto> ticketResponseDtos = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            TicketResponseDto ticketResponseDto = TicketTransformer.ticketToTicketDto(ticket);
            ticketResponseDtos.add(ticketResponseDto);
        }
        return ticketResponseDtos;
    }
}
