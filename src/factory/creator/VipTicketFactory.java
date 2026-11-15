package factory.creator;

import factory.product.Ticket;
import factory.product.VipTicket;

/** Factory Method pattern - Concrete Creator. */
public class VipTicketFactory extends TicketFactory {

    @Override
    protected Ticket createTicket(String movieTitle, String seatLabel) {
        return new VipTicket(movieTitle, seatLabel);
    }
}
