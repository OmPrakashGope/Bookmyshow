package com.project.Bookmyshow.Controller;

import com.project.Bookmyshow.Dto.AssociateShowSeatDto;
import com.project.Bookmyshow.Dto.ShowDto;
import com.project.Bookmyshow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @PostMapping("/add-show")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto)
    {
        try {
            showService.addShow(showDto);
            return new ResponseEntity<>("Show added to given date",HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/associate-seat")
    public ResponseEntity<String> associateSeat(@RequestBody AssociateShowSeatDto associateShowSeatDto)
    {
        try {
            showService.associateSeat(associateShowSeatDto);
            return new ResponseEntity<>("seat associated to given show",HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/cancel-show")
    public ResponseEntity<String> cancelShow(@RequestParam int showId)
    {
        try{
            showService.cancelShow(showId);
            return new ResponseEntity<>("Show by given show id : " + showId + "deleted from the list",HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
