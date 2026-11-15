# Movie Theater Booking

A Java console application that books cinema tickets and uses **six design patterns** working together.

| Pattern | Type | Package | What it does here |
|---|---|---|---|
| Builder | Creational | `builder` | Assembles an immutable `Booking` step by step and validates it in `build()` |
| Factory Method | Creational | `factory` | `TicketFactory` subclasses create Standard, IMAX or VIP tickets |
| Decorator | Structural | `decorator` | Wraps a ticket with extras (popcorn combo, 3D glasses, lounge access) that add to the price |
| Facade | Structural | `facade` | `BookingFacade.book(request)` hides seat, ticket, price, payment and confirmation steps |
| Strategy | Behavioral | `strategy` | Interchangeable discounts (none, student, senior) chosen per booking |
| Observer | Behavioral | `observer` | Subscribers are notified when a showtime's seat count changes |

## Structure

```
src/
  Main.java                      the only class outside the pattern packages; runs the demo
  builder/product/               Booking (+ nested Booking.Builder)
  factory/product/               Ticket, BaseTicket, StandardTicket, ImaxTicket, VipTicket
  factory/creator/               TicketFactory + Standard/Imax/Vip creators
  decorator/component/           PricedItem
  decorator/concrete/            TicketItem
  decorator/decorators/          ItemDecorator, Popcorn/3D/Lounge decorators, Extra
  strategy/strategy/             DiscountStrategy
  strategy/concrete/             NoDiscount, PercentageDiscount, StudentDiscount, SeniorDiscount
  strategy/context/              PriceCalculator
  observer/observer/             SeatObserver
  observer/subject/              Showtime
  observer/concrete/             EmailSubscriber, AppSubscriber
  facade/                        BookingFacade, BookingRequest
  facade/subsystem/              PaymentService, ConfirmationService, PaymentDeclinedException
```

## How the patterns work together

`Main` builds a `BookingRequest` and calls `BookingFacade.book()`. The facade reserves seats on the
`Showtime` (Observer notifies subscribers), asks the chosen `TicketFactory` for a ticket (Factory Method),
wraps it with the chosen extras (Decorator), applies the chosen discount (Strategy), charges the card,
and assembles the final `Booking` (Builder). If anything fails after the seats were reserved, they are
released again and the observers are told.

## Run

Requires JDK 17 or newer.

```
javac -d out $(find src -name "*.java")
java -cp out Main
```

In IntelliJ IDEA: mark `src` as the Sources Root and run `Main`.
