package com.andersen.hw.repository;

import com.andersen.hw.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.client.id IN :userIds")
    List<Ticket> findAllByUserIds(@Param("userIds") List<Integer> userIds);
}
