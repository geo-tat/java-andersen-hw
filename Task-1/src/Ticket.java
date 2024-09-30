import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private String concertHall;
    private String eventCode;
    private long time;
    private boolean isPromo;
    private char stadiumSector;
    private double maxAllowedBackpackWeightInKg;
    private LocalDateTime creationTime = LocalDateTime.now();
    private BigDecimal ticketPrice;

    public Ticket() {
    }

    public Ticket(String id, String concertHall,
                  String eventCode,
                  long time,
                  boolean isPromo,
                  char stadiumSector,
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
                  long time) {
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public char getStadiumSector() {
        return stadiumSector;
    }

    public void setStadiumSector(char stadiumSector) {
        if (stadiumSector < 'A' || stadiumSector > 'C') {
            throw new IllegalArgumentException("Must be from 'A' to 'C' ");
        }
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

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
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
