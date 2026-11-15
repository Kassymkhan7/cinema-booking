package facade.subsystem;

import java.util.concurrent.atomic.AtomicInteger;

/** Facade pattern - Subsystem class: charges a card. It knows nothing about the facade. */
public class PaymentService {

    private static final String DECLINED_CARD_PREFIX = "0000";
    private static final int LAST_DIGITS_SHOWN = 4;
    private static final int FIRST_TRANSACTION_NUMBER = 1001;

    private final AtomicInteger nextTransaction = new AtomicInteger(FIRST_TRANSACTION_NUMBER);

    public String charge(String cardNumber, double amount) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number must be provided.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to charge must be positive.");
        }
        if (cardNumber.startsWith(DECLINED_CARD_PREFIX)) {
            throw new PaymentDeclinedException("Card ending in " + lastDigits(cardNumber) + " was declined.");
        }
        return "TXN-" + nextTransaction.getAndIncrement();
    }

    private String lastDigits(String cardNumber) {
        int start = Math.max(0, cardNumber.length() - LAST_DIGITS_SHOWN);
        return cardNumber.substring(start);
    }
}
