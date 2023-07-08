package com.project.Bookmyshow.Controller;

import com.project.Bookmyshow.Dto.TheatreDto;
import com.project.Bookmyshow.Dto.TheatreSeatAddDto;
import com.project.Bookmyshow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Theater")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;
    @PostMapping("/add-theatre")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreDto theatreDto)
    {
        try {
            theatreService.addTheatre(theatreDto);
            return new ResponseEntity<>("Theatre added in the list", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/add-seat")
    public ResponseEntity<String> addSeatToTheatre(@RequestBody TheatreSeatAddDto theatreSeatAddDto)
    {
        try {
            theatreService.addSeatToTheatre(theatreSeatAddDto);
            return new ResponseEntity<>("Seats added to the given Theatre",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}