import decorator.decorators.Extra;
import facade.BookingFacade;
import facade.BookingRequest;
import facade.subsystem.ConfirmationService;
import facade.subsystem.PaymentDeclinedException;
import facade.subsystem.PaymentService;
import factory.creator.ImaxTicketFactory;
import factory.creator.StandardTicketFactory;
import factory.creator.VipTicketFactory;
import observer.concrete.AppSubscriber;
import observer.concrete.EmailSubscriber;
import observer.subject.Showtime;
import strategy.concrete.NoDiscount;
import strategy.concrete.SeniorDiscount;
import strategy.concrete.StudentDiscount;

import java.util.List;
import java.util.Locale;

/** The only class outside the pattern packages: it exercises all six patterns end to end. */
public class Main {

    private static final String VALID_CARD = "4111111111111111";
    private static final String DECLINED_CARD = "0000111122223333";

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        BookingFacade facade = new BookingFacade(new PaymentService(), new ConfirmationService());
        Showtime showtime = new Showtime("Interstellar", "19:00", 4);
        AppSubscriber appSubscriber = new AppSubscriber("Dana");
        showtime.subscribe(new EmailSubscriber("fans@cinema.kz"));
        showtime.subscribe(appSubscriber);

        demonstrateSuccessfulBooking(facade, showtime);
        demonstrateDeclinedCardRollback(facade, showtime);
        demonstrateUnsubscribeAndSoldOut(facade, showtime, appSubscriber);
        demonstrateNoSeatsLeft(facade, showtime);
    }

    private static void demonstrateSuccessfulBooking(BookingFacade facade, Showtime showtime) {
        printHeader("1. IMAX booking with extras and student discount");
        facade.book(new BookingRequest("Alice", showtime, new ImaxTicketFactory(),
                List.of("A1", "A2"), List.of(Extra.POPCORN_COMBO, Extra.GLASSES_3D),
                new StudentDiscount(), VALID_CARD));
    }

    private static void demonstrateDeclinedCardRollback(BookingFacade facade, Showtime showtime) {
        printHeader("2. VIP booking with a declined card: seats are reserved, then released");
        try {
            facade.book(new BookingRequest("Bob", showtime, new VipTicketFactory(),
                    List.of("B1", "B2"), List.of(Extra.LOUNGE_ACCESS),
                    new SeniorDiscount(), DECLINED_CARD));
        } catch (PaymentDeclinedException declined) {
            System.out.println("Payment failed: " + declined.getMessage());
            System.out.println("Seats left after rollback: " + showtime.getSeatsLeft());
        }
    }

    private static void demonstrateUnsubscribeAndSoldOut(BookingFacade facade, Showtime showtime,
                                                         AppSubscriber appSubscriber) {
        printHeader("3. Dana unsubscribes, then Bob retries with a valid card and sells the show out");
        showtime.unsubscribe(appSubscriber);
        facade.book(new BookingRequest("Bob", showtime, new VipTicketFactory(),
                List.of("B1", "B2"), List.of(Extra.LOUNGE_ACCESS),
                new SeniorDiscount(), VALID_CARD));
    }

    private static void demonstrateNoSeatsLeft(BookingFacade facade, Showtime showtime) {
        printHeader("4. Booking a sold-out show is refused");
        try {
            facade.book(new BookingRequest("Carol", showtime, new StandardTicketFactory(),
                    List.of("C1"), List.of(), new NoDiscount(), VALID_CARD));
        } catch (IllegalStateException refused) {
            System.out.println("Booking refused: " + refused.getMessage());
        }
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("--- " + title + " ---");
    }
}
