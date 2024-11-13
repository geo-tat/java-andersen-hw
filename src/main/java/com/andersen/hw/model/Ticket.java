package com.andersen.hw.model;

import com.andersen.hw.enums.SectorType;
import com.andersen.hw.enums.TicketType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "ticket")
public class Ticket implements Printable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Transient
    private String concertHall;
    @Transient
    private String eventCode;
    @Transient
    private LocalDateTime time;
    @Transient
    private boolean isPromo;
    @Transient
    private SectorType stadiumSector;
    @Transient
    private double maxAllowedBackpackWeightInKg;
    @CurrentTimestamp
    private LocalDateTime creationDate;
    @Transient
    private BigDecimal ticketPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    private static final String DATA_TIME_FORMAT = "yyyy.MM.dd, HH:mm";

    public Ticket(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Ticket(TicketType ticketType, User client) {
        this.ticketType = ticketType;
        this.client = client;
    }

    public Ticket(Integer ticketId, TicketType ticketType, User client, LocalDateTime creationDate) {
        this.id = ticketId;
        this.ticketType = ticketType;
        this.client = client;
        this.creationDate = creationDate;
    }

    public Ticket() {
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
                ",\ncreationTime=" + creationDate.format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT)) +
                ",\nticketPrice=" + ticketPrice + Currency.getInstance(Locale.US).getSymbol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(creationDate, ticket.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate);
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
                ", creationTime=" + creationDate +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
