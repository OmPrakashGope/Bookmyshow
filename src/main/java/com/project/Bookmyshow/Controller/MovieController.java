package com.project.Bookmyshow.Controller;

import com.project.Bookmyshow.Dto.RequestDtos.AddMovieDto;
import com.project.Bookmyshow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody AddMovieDto addMovieDto)
    {
        movieService.addMovie(addMovieDto);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }
    @PutMapping("/delete-movie")
    public ResponseEntity<String> deleteMovie(@RequestParam int movieId)
    {
        try {
            movieService.deleteMovie(movieId);
            return new ResponseEntity<>("movie removed from the list", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-max-show-movieName")
    public ResponseEntity<List<String>> getMovieWithMaxShow()
    {
       List<String> movieName = movieService.getMovieWithMaxShow();
       return new ResponseEntity<>(movieName,HttpStatus.OK);
    }
    @GetMapping("/totalCollection/{movieId}")
    public ResponseEntity<Double> totalCollection(@PathVariable Integer movieId) {
        try {
            Double result = movieService.totalCollection(movieId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
