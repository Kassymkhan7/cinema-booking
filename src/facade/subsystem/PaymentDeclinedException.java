package facade.subsystem;

/** Thrown when the payment provider refuses to charge the card. */
public class PaymentDeclinedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaymentDeclinedException(String message) {
        super(message);
    }
}
