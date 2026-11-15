package decorator.decorators;

import decorator.component.PricedItem;

import java.util.function.UnaryOperator;

/**
 * The extras a customer can pick. Each constant knows which decorator wraps it,
 * so callers never need an if/switch to choose one.
 */
public enum Extra {
    POPCORN_COMBO(PopcornComboDecorator::new),
    GLASSES_3D(ThreeDGlassesDecorator::new),
    LOUNGE_ACCESS(LoungeAccessDecorator::new);

    private final UnaryOperator<PricedItem> wrapper;

    Extra(UnaryOperator<PricedItem> wrapper) {
        this.wrapper = wrapper;
    }

    public PricedItem applyTo(PricedItem item) {
        return wrapper.apply(item);
    }
}
