package factory.product;

/** Shared state and behaviour of all tickets, so the concrete products only define what differs. */
public abstract class BaseTicket implements Ticket {

    private final String movieTitle;
    private final String seatLabel;

    protected BaseTicket(String movieTitle, String seatLabel) {
        this.movieTitle = movieTitle;
        this.seatLabel = seatLabel;
    }

    @Override
    public String getMovieTitle() {
        return movieTitle;
    }

    @Override
    public String getSeatLabel() {
        return seatLabel;
    }

    @Override
    public String describe() {
        return getHallName() + " ticket, seat " + seatLabel;
    }
}
