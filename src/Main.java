import enums.SectorType;
import model.Ticket;
import service.TicketService;
import service.TicketServiceImpl;
import storage.TicketStorageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        TicketService service = new TicketServiceImpl(new TicketStorageImpl());
        BigDecimal price = BigDecimal.valueOf(100);
        LocalDateTime timofEvent = LocalDateTime.of(2024, 12, 31, 12, 0);

        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket("000" + i, "Hall", "010", timofEvent, true, SectorType.C,
                    23.0, price);
            service.addTicket(ticket);
        }

        service.getById("0007").printTicketInfo();

    }
}