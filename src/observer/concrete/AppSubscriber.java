package observer.concrete;

import observer.observer.SeatObserver;

/** Observer pattern - Concrete Observer: sends a push alert only when seats are running low. */
public class AppSubscriber implements SeatObserver {

    private static final int LOW_SEATS_THRESHOLD = 2;

    private final String userName;

    public AppSubscriber(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String movieTitle, int seatsLeft) {
        if (seatsLeft > LOW_SEATS_THRESHOLD) {
            return;
        }
        String alert = seatsLeft == 0 ? "SOLD OUT" : "hurry, only " + seatsLeft + " seat(s) left";
        System.out.printf("   [Push to %s] %s - %s%n", userName, movieTitle, alert);
    }
}
