package factory.product;

/** Factory Method pattern - Product: the contract every ticket type fulfils. */
public interface Ticket {

    String getMovieTitle();

    String getSeatLabel();

    String getHallName();

    double getBasePrice();

    String describe();
}
