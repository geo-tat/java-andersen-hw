package com.andersen.hw.model;

import com.andersen.hw.enums.SectorType;
import com.andersen.hw.enums.TicketType;
import com.andersen.hw.util.IdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

@Setter
@Getter
public class Ticket implements Printable {

    private Integer id;
    private String concertHall;
    private String eventCode;
    private LocalDateTime time;
    private boolean isPromo;
    private SectorType stadiumSector;
    private double maxAllowedBackpackWeightInKg;
    private LocalDateTime creationTime;
    private BigDecimal ticketPrice;
    private User client;
    private TicketType ticketType;

    private static final String DATA_TIME_FORMAT = "yyyy.MM.dd, HH:mm";


    public Ticket(TicketType ticketType, User client) {
        this.ticketType = ticketType;
        this.client = client;
        this.id = IdGenerator.generateId();
    }

    public Ticket(Integer ticketId, TicketType ticketType, User client, LocalDateTime creationTime) {
        this.id = ticketId;
        this.ticketType = ticketType;
        this.client = client;
        this.creationTime = creationTime;
    }

    public Ticket() {
        this.id = IdGenerator.generateId();
        this.concertHall = "";
        this.eventCode = "";
        this.isPromo = false;
        this.maxAllowedBackpackWeightInKg = 0.0;
        this.ticketPrice = BigDecimal.ZERO;
    }

    public Ticket(Long ticketId, String concertHall,

                  String eventCode,
                  LocalDateTime time,
                  boolean isPromo,
                  SectorType stadiumSector,
                  double maxAllowedBackpackWeightInKg,
                  BigDecimal ticketPrice) {

        if (ticketId > 9999) {
            throw new IllegalArgumentException("Size over 4 symbols");
        }
        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Size over 10 chars");
        }

        if (eventCode.length() != 3 || !eventCode.matches("\\d{3}")) {
            throw new IllegalArgumentException("Must be 3 digits");
        }

        if (maxAllowedBackpackWeightInKg < 0) {
            throw new IllegalArgumentException("Invalid argument");
        }

        if (ticketPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid argument");
        }

        this.id = IdGenerator.generateId();
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxAllowedBackpackWeightInKg = maxAllowedBackpackWeightInKg;
        this.ticketPrice = ticketPrice;
    }

    public Ticket(String concertHall,
                  String eventCode,
                  LocalDateTime time) {


        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Size over 10 chars");
        }
        this.concertHall = concertHall;

        if (eventCode.length() != 3 || !eventCode.matches("\\d{3}")) {
            throw new IllegalArgumentException("Must be 3 digits");
        }
        this.eventCode = eventCode;
        this.time = time;
        this.id = IdGenerator.generateId();
        this.isPromo = false;
        this.maxAllowedBackpackWeightInKg = 0.0;
        this.ticketPrice = BigDecimal.ZERO;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void printTicketInfo() {
        System.out.println("Ticket:" +

                "\nid='" + id + '\'' +
                ",\nconcertHall='" + concertHall + '\'' +
                ",\neventCode='" + eventCode + '\'' +
                ",\ntime=" + time.format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT)) +
                ",\nisPromo=" + isPromo +
                ",\nstadiumSector=" + stadiumSector +
                ",\nmaxAllowedBackpackWeightInKg=" + maxAllowedBackpackWeightInKg +
                ",\ncreationTime=" + creationTime.format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT)) +
                ",\nticketPrice=" + ticketPrice + Currency.getInstance(Locale.US).getSymbol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(creationTime, ticket.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxAllowedBackpackWeightInKg=" + maxAllowedBackpackWeightInKg +
                ", creationTime=" + creationTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
