import enums.SectorType;
import model.Admin;
import model.Client;
import model.Ticket;
import model.User;
import service.TicketService;
import service.TicketServiceImpl;
import storage.TicketStorageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


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

        Admin admin = new Admin();
        Client client = new Client();


        List<User> users = List.of(client, admin);


        client.geId();
        client.getTicket();


        // checking overriding print() and class Id
        client.print();

        // checking share methods
        service.share("0001", "+0303939399");
        service.share("0002", "+39341340", "test@test.com");
    }

}