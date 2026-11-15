package factory.creator;

import factory.product.Ticket;

/**
 * Factory Method pattern - Creator.
 * Declares the factory method and holds shared logic that works for every hall type.
 */
public abstract class TicketFactory {

    /** The factory method: each concrete creator decides which Ticket to instantiate. */
    protected abstract Ticket createTicket(String movieTitle, String seatLabel);

    public Ticket issueTicket(String movieTitle, String seatLabel) {
        requireText(movieTitle, "Movie title");
        requireText(seatLabel, "Seat label");
        return createTicket(movieTitle, seatLabel);
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must be provided.");
        }
    }
}
