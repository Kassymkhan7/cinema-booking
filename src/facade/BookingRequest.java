package facade;

import decorator.decorators.Extra;
import factory.creator.TicketFactory;
import observer.subject.Showtime;
import strategy.strategy.DiscountStrategy;

import java.util.List;

/** Parameter object: everything the client decides, so the facade has a single-argument API. */
public record BookingRequest(
        String customerName,
        Showtime showtime,
        TicketFactory ticketFactory,
        List<String> seatLabels,
        List<Extra> extras,
        DiscountStrategy discountStrategy,
        String cardNumber) {
}
