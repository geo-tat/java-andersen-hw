import java.math.BigDecimal;

public class TicketService {

    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        Ticket fullTicket = new Ticket("A3F1","Hall","043",133134,true,
                'C',30, BigDecimal.valueOf(50));
        Ticket limitedTicket = new Ticket("ConHall","054",300000);

        System.out.println(fullTicket.toString());
        System.out.println(limitedTicket.toString());
    }
}
