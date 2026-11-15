package strategy.concrete;

import strategy.strategy.DiscountStrategy;

/** Strategy pattern - Concrete Strategy: full price. */
public class NoDiscount implements DiscountStrategy {

    @Override
    public String getName() {
        return "No discount";
    }

    @Override
    public double apply(double subtotal) {
        return subtotal;
    }
}
