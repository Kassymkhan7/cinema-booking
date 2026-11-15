package decorator.decorators;

import decorator.component.PricedItem;

import java.util.Objects;

/** Decorator pattern - Base Decorator: wraps a PricedItem and delegates to it by default. */
public abstract class ItemDecorator implements PricedItem {

    private final PricedItem wrapped;

    protected ItemDecorator(PricedItem wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped, "Wrapped item must not be null.");
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }
}
