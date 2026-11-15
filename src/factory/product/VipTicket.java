package factory.product;

/** Factory Method pattern - Concrete Product. */
public class VipTicket extends BaseTicket {

    private static final String HALL_NAME = "VIP";
    private static final double BASE_PRICE = 18.00;

    public VipTicket(String movieTitle, String seatLabel) {
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
