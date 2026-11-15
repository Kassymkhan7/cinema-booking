package factory.product;

/** Factory Method pattern - Concrete Product. */
public class StandardTicket extends BaseTicket {

    private static final String HALL_NAME = "Standard";
    private static final double BASE_PRICE = 8.00;

    public StandardTicket(String movieTitle, String seatLabel) {
        super(movieTitle, seatLabel);
    }

    @Override
    public String getHallName() {
        return HALL_NAME;
    }

    @Override
    public double getBasePrice() {
        return BASE_PRICE;
    }
}
