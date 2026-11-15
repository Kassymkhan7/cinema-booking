package builder.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Builder pattern - Product.
 * An immutable booking that can only be created through {@link Booking.Builder}.
 */
public final class Booking {

    private final String bookingId;
    private final String customerName;
    private final String movieTitle;
    private final String startTime;
    private final List<String> seats;
    private final List<String> ticketLines;
    private final String discountName;
    private final double totalPrice;
    private final String transactionId;

    private Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.customerName = builder.customerName;
        this.movieTitle = builder.movieTitle;
        this.startTime = builder.startTime;
        this.seats = List.copyOf(builder.seats);
        this.ticketLines = List.copyOf(builder.ticketLines);
        this.discountName = builder.discountName;
        this.totalPrice = builder.totalPrice;
        this.transactionId = builder.transactionId;
    }

    public String getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public String getMovieTitle() { return movieTitle; }
    public List<String> getSeats() { return seats; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("=== Booking %s ===%n", bookingId));
        receipt.append(String.format("Customer : %s%n", customerName));
        receipt.append(String.format("Movie    : %s (%s)%n", movieTitle, startTime));
        receipt.append(String.format("Seats    : %s%n", String.join(", ", seats)));
        for (String line : ticketLines) {
            receipt.append(String.format("  - %s%n", line));
        }
        receipt.append(String.format("Discount : %s%n", discountName));
        receipt.append(String.format("Total    : $%.2f%n", totalPrice));
        receipt.append(String.format("Payment  : %s", transactionId));
        return receipt.toString();
    }

    /** Builder pattern - Builder: collects the parts step by step and validates them in build(). */
    public static class Builder {

        private static final AtomicInteger NEXT_ID = new AtomicInteger(1);
        private static final String ID_PREFIX = "BK-";
        private static final String DEFAULT_DISCOUNT = "No discount";

        private final List<String> seats = new ArrayList<>();
        private final List<String> ticketLines = new ArrayList<>();
        private String bookingId;
        private String customerName;
        private String movieTitle;
        private String startTime;
        private String discountName = DEFAULT_DISCOUNT;
        private double totalPrice;
        private String transactionId;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder movieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        public Builder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder addSeat(String seatLabel) {
            this.seats.add(seatLabel);
            return this;
        }

        public Builder addTicketLine(String ticketLine) {
            this.ticketLines.add(ticketLine);
            return this;
        }

        public Builder discountName(String discountName) {
            this.discountName = discountName;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Booking build() {
            validate();
            this.bookingId = String.format("%s%04d", ID_PREFIX, NEXT_ID.getAndIncrement());
            return new Booking(this);
        }

        private void validate() {
            requireText(customerName, "Customer name");
            requireText(movieTitle, "Movie title");
            requireText(startTime, "Start time");
            requireText(transactionId, "Transaction id");
            if (seats.isEmpty()) {
                throw new IllegalStateException("A booking needs at least one seat.");
            }
            if (totalPrice < 0) {
                throw new IllegalStateException("Total price cannot be negative.");
            }
        }

        private static void requireText(String value, String fieldName) {
            if (value == null || value.isBlank()) {
                throw new IllegalStateException(fieldName + " must be set.");
            }
        }
    }
}
