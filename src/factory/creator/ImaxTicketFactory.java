package factory.creator;

import factory.product.ImaxTicket;
import factory.product.Ticket;

/** Factory Method pattern - Concrete Creator. */
public class ImaxTicketFactory extends TicketFactory {

    @Override
    protected Ticket createTicket(String movieTitle, String seatLabel) {
        return new ImaxTicket(movieTitle, seatLabel);
    }
}
