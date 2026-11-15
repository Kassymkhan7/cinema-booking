package strategy.strategy;

/** Strategy pattern - Strategy: one interchangeable way to discount a subtotal. */
public interface DiscountStrategy {

    String getName();

    double apply(double subtotal);
}
