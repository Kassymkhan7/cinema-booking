package facade.subsystem;

import builder.product.Booking;

/** Facade pattern - Subsystem class: delivers the booking confirmation to the customer. */
public class ConfirmationService {

    public void send(Booking booking) {
        System.out.println(booking);
        System.out.println("Confirmation sent to " + booking.getCustomerName() + ".");
    }
}
