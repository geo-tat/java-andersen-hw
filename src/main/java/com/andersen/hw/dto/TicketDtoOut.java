package com.andersen.hw.dto;

import com.andersen.hw.enums.TicketType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
