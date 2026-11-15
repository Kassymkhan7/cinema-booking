package decorator.decorators;

import decorator.component.PricedItem;

/** Decorator pattern - Concrete Decorator: adds a popcorn combo. */
public class PopcornComboDecorator extends ItemDecorator {

    private static final double COMBO_PRICE = 5.50;

    public PopcornComboDecorator(PricedItem wrapped) {
        super(wrapped);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + COMBO_PRICE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + popcorn combo";
    }
}
