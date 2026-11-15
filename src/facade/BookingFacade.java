package facade;

import builder.product.Booking;
import decorator.component.PricedItem;
import decorator.concrete.TicketItem;
import decorator.decorators.Extra;
import facade.subsystem.ConfirmationService;
import facade.subsystem.PaymentService;
import factory.product.Ticket;
import observer.subject.Showtime;
import strategy.context.PriceCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade pattern - Facade.
 * One call, book(), coordinates seats (Observer), tickets (Factory Method), extras (Decorator),
 * pricing (Strategy), payment and the final Booking (Builder) in the correct order.
 */
public class BookingFacade {

    private final PaymentService paymentService;
    private final ConfirmationService confirmationService;

    public BookingFacade(PaymentService paymentService, ConfirmationService confirmationService) {
        this.paymentService = paymentService;
        this.confirmationService = confirmationService;
    }

    public Booking book(BookingRequest request) {
        Showtime showtime = request.showtime();
        int seatCount = request.seatLabels().size();

        showtime.reserveSeats(seatCount);
        try {
            return completeBooking(request);
        } catch (RuntimeException failure) {
            // Any failure after reserving (declined card, bad seat label...) must free the seats again.
            showtime.releaseSeats(seatCount);
            throw failure;
        }
    }

    private Booking completeBooking(BookingRequest request) {
        List<String> ticketLines = new ArrayList<>();
        double subtotal = 0;
        for (String seatLabel : request.seatLabels()) {
            PricedItem item = priceSeat(request, seatLabel);
            subtotal += item.getPrice();
            ticketLines.add(String.format("%s - $%.2f", item.getDescription(), item.getPrice()));
        }

        PriceCalculator calculator = new PriceCalculator(request.discountStrategy());
        double total = calculator.calculateTotal(subtotal);
        String transactionId = paymentService.charge(request.cardNumber(), total);

        Booking booking = buildBooking(request, ticketLines, calculator.getStrategyName(), total, transactionId);
        confirmationService.send(booking);
        return booking;
    }

    private PricedItem priceSeat(BookingRequest request, String seatLabel) {
        Ticket ticket = request.ticketFactory().issueTicket(request.showtime().getMovieTitle(), seatLabel);
        PricedItem item = new TicketItem(ticket);
        for (Extra extra : request.extras()) {
            item = extra.applyTo(item);
        }
        return item;
    }

    private Booking buildBooking(BookingRequest request, List<String> ticketLines,
                                 String discountName, double total, String transactionId) {
        Booking.Builder builder = new Booking.Builder()
                .customerName(request.customerName())
                .movieTitle(request.showtime().getMovieTitle())
                .startTime(request.showtime().getStartTime())
                .discountName(discountName)
                .totalPrice(total)
                .transactionId(transactionId);
        request.seatLabels().forEach(builder::addSeat);
        ticketLines.forEach(builder::addTicketLine);
        return builder.build();
    }
}
