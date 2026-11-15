package strategy.context;

import strategy.strategy.DiscountStrategy;

import java.util.Objects;

/**
 * Strategy pattern - Context.
 * Delegates the discount algorithm to whichever strategy it holds; it never checks which one that is.
 */
public final class PriceCalculator {

    private static final double CENTS_PER_UNIT = 100.0;

    private DiscountStrategy strategy;

    public PriceCalculator(DiscountStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Discount strategy must not be null.");
    }

    public String getStrategyName() {
        return strategy.getName();
    }

    public double calculateTotal(double subtotal) {
        double discounted = strategy.apply(subtotal);
        return Math.round(discounted * CENTS_PER_UNIT) / CENTS_PER_UNIT;
    }
}
