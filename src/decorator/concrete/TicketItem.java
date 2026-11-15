package decorator.concrete;

import decorator.component.PricedItem;
import factory.product.Ticket;

/** Decorator pattern - Concrete Component: a plain ticket that extras can be wrapped around. */
public class TicketItem implements PricedItem {

    private final Ticket ticket;

    public TicketItem(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public double getPrice() {
        return ticket.getBasePrice();
    }

    @Override
    public String getDescription() {
        return ticket.describe();
    }
}
