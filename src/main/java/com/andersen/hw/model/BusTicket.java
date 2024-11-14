package com.andersen.hw.model;

import lombok.Data;

@Data
public class BusTicket {

  private String ticketClass;

  private String ticketType;

  private String startDate;

  private String price;
}
