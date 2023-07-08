package com.project.Bookmyshow.Module;

import com.project.Bookmyshow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;
import com.project.Bookmyshow.Enum.*;

@Entity
@Table(name = "Showseat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private double price;
    private Boolean isAvailable;
    private Boolean isFoodAttached;
    @ManyToOne
    @JoinColumn
    private Show show;

}
