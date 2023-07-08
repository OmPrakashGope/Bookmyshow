package com.project.Bookmyshow.Module;

import com.project.Bookmyshow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.Bookmyshow.Enum.*;

@Entity
@Table(name = "TheaterSeat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private String seatNumber;
    @ManyToOne
    @JoinColumn
    private Theatre theatre;
}
