package observer.concrete;

import observer.observer.SeatObserver;

/** Observer pattern - Concrete Observer: emails the new seat count after every change. */
public class EmailSubscriber implements SeatObserver {

    private final String emailAddress;

    public EmailSubscriber(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void update(String movieTitle, int seatsLeft) {
        String status = seatsLeft == 0 ? "SOLD OUT" : seatsLeft + " seat(s) left";
        System.out.printf("   [Email to %s] %s: %s%n", emailAddress, movieTitle, status);
    }
}
