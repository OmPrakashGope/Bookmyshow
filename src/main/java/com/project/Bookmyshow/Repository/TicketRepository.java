package com.project.Bookmyshow.Repository;
import com.project.Bookmyshow.Module.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
