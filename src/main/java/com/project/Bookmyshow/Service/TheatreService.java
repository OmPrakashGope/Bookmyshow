package com.project.Bookmyshow.Service;

import com.project.Bookmyshow.Dto.TheatreDto;
import com.project.Bookmyshow.Dto.TheatreSeatAddDto;
import com.project.Bookmyshow.ExceptionHandling.TheatreAlreadyExistException;
import com.project.Bookmyshow.ExceptionHandling.TheatreNotPresentException;
import com.project.Bookmyshow.Repository.TheatreRepository;
import com.project.Bookmyshow.Repository.TheatreSeatRepository;
import com.project.Bookmyshow.Transformer.TheatreTransformer;
import com.project.Bookmyshow.Enum.SeatType;
import com.project.Bookmyshow.Module.Theatre;
import com.project.Bookmyshow.Module.TheatreSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private TheatreSeatRepository theatreSeatRepository;
    public void addTheatre(TheatreDto theatreDto) throws TheatreAlreadyExistException{
        Optional<Theatre> theatreOptional = theatreRepository.findByLocation(theatreDto.getLocation());
        if(theatreOptional.isPresent())
        {
            throw new TheatreAlreadyExistException("Theatre in the given location already exists");
        }
        Theatre theatre = TheatreTransformer.TheatreDtoToEntity(theatreDto);
        theatreRepository.save(theatre);
    }

    public void addSeatToTheatre(TheatreSeatAddDto theatreSeatAddDto) throws TheatreNotPresentException{
        int seatsInRow = theatreSeatAddDto.getNumberOfSeatsInOneRow();
        Optional<Theatre> theatreOptional = theatreRepository.findByLocation(theatreSeatAddDto.getLocation());
        if(theatreOptional.isEmpty())
        {
            throw new TheatreNotPresentException("Theatre at given location is not present");
        }
        Theatre theatre = theatreOptional.get();
        char prefix= 'A';
        int classicSeat = theatreSeatAddDto.getClassicSeats();
        int count = 1;
        int seatNumberCount = 0;
        for(int i = 1;i <= classicSeat;)
        {
            int suffix = 1;
            for(int j = 0;j < seatsInRow && i <= classicSeat;j++) {
                String seatNumber = prefix + "" + suffix;
                TheatreSeat theatreSeat = new TheatreSeat();
                theatreSeat.setSeatNumber(seatNumber);
                theatreSeat.setSeatType(SeatType.CLASSIC);
                theatreSeat.setTheatre(theatre);
                theatre.getTheatreSeatList().add(theatreSeat);
                suffix++;
                i++;
            }
                prefix++;

        }
        int premiumSeat = theatreSeatAddDto.getPremiumSeats();
        for(int i = 1;i <= premiumSeat;i++)
        {
            int suffix = 1;
            for(int j = 0;j < seatsInRow && i <= premiumSeat;j++) {
                String seatNumber = prefix + "" + suffix;
                System.out.println(seatNumber);
                TheatreSeat theatreSeat = new TheatreSeat();
                theatreSeat.setSeatNumber(seatNumber);
                theatreSeat.setSeatType(SeatType.PREMIUM);
                theatreSeat.setTheatre(theatre);
                theatre.getTheatreSeatList().add(theatreSeat);
                suffix++;
                i++;
            }
            prefix++;
        }
        theatreRepository.save(theatre);
    }
}
