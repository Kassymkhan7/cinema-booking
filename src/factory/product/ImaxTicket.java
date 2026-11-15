package factory.product;

/** Factory Method pattern - Concrete Product. */
public class ImaxTicket extends BaseTicket {

    private static final String HALL_NAME = "IMAX";
    private static final double BASE_PRICE = 12.50;

    public ImaxTicket(String movieTitle, String seatLabel) {
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
