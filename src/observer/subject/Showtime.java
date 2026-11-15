package observer.subject;

import observer.observer.SeatObserver;

import java.util.ArrayList;
import java.util.List;

/** Observer pattern - Subject: a screening that notifies subscribers whenever its seat count changes. */
public class Showtime {

    private final String movieTitle;
    private final String startTime;
    private final int totalSeats;
    private final List<SeatObserver> observers = new ArrayList<>();
    private int seatsLeft;

    public Showtime(String movieTitle, String startTime, int totalSeats) {
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("A showtime needs at least one seat.");
        }
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.totalSeats = totalSeats;
        this.seatsLeft = totalSeats;
    }

    public String getMovieTitle() { return movieTitle; }
    public String getStartTime() { return startTime; }
    public int getSeatsLeft() { return seatsLeft; }

    public void subscribe(SeatObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(SeatObserver observer) {
        observers.remove(observer);
    }

    public void reserveSeats(int count) {
        requirePositive(count);
        if (count > seatsLeft) {
            throw new IllegalStateException(
                    "Only " + seatsLeft + " seat(s) left for " + movieTitle + ", cannot reserve " + count + ".");
        }
        seatsLeft -= count;
        notifyObservers();
    }

    public void releaseSeats(int count) {
        requirePositive(count);
        seatsLeft = Math.min(totalSeats, seatsLeft + count);
        notifyObservers();
    }

    private void requirePositive(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Seat count must be positive.");
        }
    }

    private void notifyObservers() {
        for (SeatObserver observer : observers) {
            observer.update(movieTitle, seatsLeft);
        }
    }
}
