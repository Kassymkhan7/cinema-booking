package decorator.decorators;

import decorator.component.PricedItem;

/** Decorator pattern - Concrete Decorator: adds 3D glasses. */
public class ThreeDGlassesDecorator extends ItemDecorator {

    private static final double GLASSES_PRICE = 1.50;

    public ThreeDGlassesDecorator(PricedItem wrapped) {
        super(wrapped);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + GLASSES_PRICE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 3D glasses";
    }
}
