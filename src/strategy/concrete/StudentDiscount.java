package strategy.concrete;

/** Strategy pattern - Concrete Strategy: student pricing. */
public class StudentDiscount extends PercentageDiscount {

    private static final int PERCENT_OFF = 20;

    public StudentDiscount() {
        super("Student", PERCENT_OFF);
    }
}
