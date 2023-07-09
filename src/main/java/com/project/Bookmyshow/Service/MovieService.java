package com.project.Bookmyshow.Service;

import com.project.Bookmyshow.Dto.AddMovieDto;
import com.project.Bookmyshow.ExceptionHandling.MovieNotFoundException;
import com.project.Bookmyshow.Repository.MovieRepository;
import com.project.Bookmyshow.Transformer.MovieTransformer;
import com.project.Bookmyshow.Module.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public void addMovie(AddMovieDto addMovieDto) {
        Movie movie = MovieTransformer.movieDtoToEntity(addMovieDto);
        movieRepository.save(movie);
    }

    public void deleteMovie(int id) throws MovieNotFoundException{
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isEmpty())
        {
            throw new MovieNotFoundException("movie of given id does not exist in the list");
        }
        movieRepository.deleteById(id);
    }

    public List<String> getMovieWithMaxShow() {
        List<Movie> movieList = movieRepository.findAll();
        int maxShows = 0;
        List<String> movieName = new ArrayList<>();
        for(Movie movie : movieList)
        {
            if(movie.getShowList().size() > maxShows)
            {
                maxShows = movie.getShowList().size();
            }
        }
        for(Movie movie : movieList)
        {
            if(movie.getShowList().size() == maxShows)
            {
                movieName.add(movie.getMovieName());
            }
        }
        return movieName;
    }
}
