package factory.creator;

import factory.product.StandardTicket;
import factory.product.Ticket;

/** Factory Method pattern - Concrete Creator. */
public class StandardTicketFactory extends TicketFactory {

    @Override
    protected Ticket createTicket(String movieTitle, String seatLabel) {
        return new StandardTicket(movieTitle, seatLabel);
    }
}
