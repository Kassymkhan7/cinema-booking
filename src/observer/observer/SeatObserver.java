package observer.observer;

/** Observer pattern - Observer: anyone who wants to hear about seat availability changes. */
public interface SeatObserver {

    void update(String movieTitle, int seatsLeft);
}
