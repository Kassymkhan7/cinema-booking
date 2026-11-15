package decorator.component;

/** Decorator pattern - Component: anything that has a description and a price. */
public interface PricedItem {

    double getPrice();

    String getDescription();
}
