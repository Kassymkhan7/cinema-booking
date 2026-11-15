package strategy.concrete;

/** Strategy pattern - Concrete Strategy: senior pricing. */
public class SeniorDiscount extends PercentageDiscount {

    private static final int PERCENT_OFF = 30;

    public SeniorDiscount() {
        super("Senior", PERCENT_OFF);
    }
}
