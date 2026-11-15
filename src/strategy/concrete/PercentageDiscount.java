package strategy.concrete;

import strategy.strategy.DiscountStrategy;

/** Shared maths for every percentage-based discount, so subclasses only supply a label and a rate. */
public abstract class PercentageDiscount implements DiscountStrategy {

    private static final double PERCENT_BASE = 100.0;

    private final String label;
    private final int percentOff;

    protected PercentageDiscount(String label, int percentOff) {
        this.label = label;
        this.percentOff = percentOff;
    }

    @Override
    public String getName() {
        return label + " (" + percentOff + "% off)";
    }

    @Override
    public double apply(double subtotal) {
        return subtotal * (PERCENT_BASE - percentOff) / PERCENT_BASE;
    }
}
