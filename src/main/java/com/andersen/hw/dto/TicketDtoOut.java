package com.andersen.hw.dto;

import com.andersen.hw.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDtoOut {

    private Integer id;

    private LocalDateTime creationDate;

    private Integer clientId;

    private TicketType ticketType;

}
