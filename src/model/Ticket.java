package model;

import enums.SectorType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class Ticket {
    private String id;
    private String concertHall;
    private String eventCode;
    private LocalDateTime time;
    private boolean isPromo;
    private SectorType stadiumSector;
    private double maxAllowedBackpackWeightInKg;
    private final LocalDateTime creationTime = LocalDateTime.now();
    private BigDecimal ticketPrice;

    private static final String DATA_TIME_FORMAT = "yyyy.MM.dd, HH:mm";

    public Ticket() {
    }

    public Ticket(String id, String concertHall,
                  String eventCode,
                  LocalDateTime time,
                  boolean isPromo,
                  SectorType stadiumSector,
                  double maxAllowedBackpackWeightInKg,
                  BigDecimal ticketPrice) {
        setId(id);
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = time;
        this.isPromo = isPromo;
        setStadiumSector(stadiumSector);
        setMaxAllowedBackpackWeightInKg(maxAllowedBackpackWeightInKg);
        setTicketPrice(ticketPrice);
    }

    public Ticket(String concertHall,
                  String eventCode,
                  LocalDateTime time) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.length() > 4) {
            throw new IllegalArgumentException("Size over 4 symbols");
        }
        this.id = id;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(String concertHall) {
        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Size over 10 chars");
        }
        this.concertHall = concertHall;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        if (eventCode.length() != 3 || !eventCode.matches("\\d{3}")) {
            throw new IllegalArgumentException("Must be 3 digits");
        }
        this.eventCode = eventCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public SectorType getStadiumSector() {
        return stadiumSector;
    }

    public void setStadiumSector(SectorType stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public double getMaxAllowedBackpackWeightInKg() {
        return maxAllowedBackpackWeightInKg;
    }

    public void setMaxAllowedBackpackWeightInKg(double maxAllowedBackpackWeightInKg) {
        if (maxAllowedBackpackWeightInKg < 0) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.maxAllowedBackpackWeightInKg = maxAllowedBackpackWeightInKg;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        if (ticketPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.ticketPrice = ticketPrice;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
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
}
