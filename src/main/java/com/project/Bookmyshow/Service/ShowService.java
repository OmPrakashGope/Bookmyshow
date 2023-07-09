package com.project.Bookmyshow.Service;

import com.project.Bookmyshow.Dto.AssociateShowSeatDto;
import com.project.Bookmyshow.Dto.ShowDto;
import com.project.Bookmyshow.ExceptionHandling.MovieNotFoundException;
import com.project.Bookmyshow.ExceptionHandling.ShowNotFoundException;
import com.project.Bookmyshow.ExceptionHandling.TheatreNotFoundException;
import com.project.Bookmyshow.Repository.MovieRepository;
import com.project.Bookmyshow.Repository.ShowRepository;
import com.project.Bookmyshow.Repository.ShowSeatRepository;
import com.project.Bookmyshow.Repository.TheatreRepository;
import com.project.Bookmyshow.Transformer.ShowTransformer;
import com.project.Bookmyshow.Enum.SeatType;
import com.project.Bookmyshow.Module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    public void addShow(ShowDto showDto)throws MovieNotFoundException,TheatreNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(showDto.getMovieId());
        if(movieOptional.isEmpty())
        {
            throw new MovieNotFoundException("Given movie is not in the list");
        }
        Optional<Theatre> theatreOptional = theatreRepository.findById(showDto.getTheatreId());
        if(theatreOptional.isEmpty())
        {
            throw new TheatreNotFoundException("Given theatre is not in the list");
        }
        Theatre theatre = theatreOptional.get();
        Movie movie = movieOptional.get();
        Show show = ShowTransformer.showDtoToEntity(showDto);
        show.setTheater(theatre);
        show.setMovie(movie);
        show = showRepository.save(show);
        theatre.getShowList().add(show);
        theatreRepository.save(theatre);
        movie.getShowList().add(show);
        movieRepository.save(movie);
    }
    public void associateSeat(AssociateShowSeatDto associateShowSeatDto) throws ShowNotFoundException{
        Optional<Show> showOptional = showRepository.findById(associateShowSeatDto.getShowId());
        if(showOptional.isEmpty())
        {
            throw new ShowNotFoundException("given show does not exist");
        }
        Show show = showOptional.get();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList)
        {
            showSeatRepository.delete(showSeat);
        }
        showSeatList.clear();
        System.out.println(showSeatList.size());
        List<TheatreSeat> theatreSeats = show.getTheater().getTheatreSeatList();
        for(TheatreSeat theatreSeat:theatreSeats)
        {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNumber(theatreSeat.getSeatNumber());
            showSeat.setSeatType(theatreSeat.getSeatType());
            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
            {
                showSeat.setPrice(associateShowSeatDto.getPriceForClassicSeats());
            }
            else
            {
                showSeat.setPrice(associateShowSeatDto.getPriceForPremiumSeats());
            }
            showSeat.setShow(show);
            showSeat.setIsAvailable(true);
            showSeat.setIsFoodAttached(false);
            show.getShowSeatList().add(showSeat);
        }
        System.out.println(showSeatList.size());
        showRepository.save(show);
    }
}
