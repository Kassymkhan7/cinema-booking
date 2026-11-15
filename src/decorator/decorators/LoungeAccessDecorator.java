package decorator.decorators;

import decorator.component.PricedItem;

/** Decorator pattern - Concrete Decorator: adds lounge access. */
public class LoungeAccessDecorator extends ItemDecorator {

    private static final double LOUNGE_PRICE = 7.00;

    public LoungeAccessDecorator(PricedItem wrapped) {
        super(wrapped);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + LOUNGE_PRICE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + lounge access";
    }
}
