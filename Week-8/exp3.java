import java.util.*;
abstract class Seat {
    private String seatNumber;
    public Seat(String seatNumber) { this.seatNumber = seatNumber; }
    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}
class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) { super(seatNumber); }
    public double getPrice() { return 150.0; }
}
class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) { super(seatNumber); }
    public double getPrice() { return 250.0; }
}
class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) { super(seatNumber); }
    public double getPrice() { return 400.0; }
}
class Customer {
    private String name;
    public Customer(String name) { this.name = name; }
    public String getName() { return name; }
}
class Show {
    private String time;
    private Set<String> bookedSeats = new HashSet<>();
    public Show(String time) { this.time = time; }
    public boolean isSeatBooked(String seatNumber) { return bookedSeats.contains(seatNumber); }
    public void bookSeat(String seatNumber) { bookedSeats.add(seatNumber); }
    public void releaseSeat(String seatNumber) { bookedSeats.remove(seatNumber); }
}
class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }
    public Customer getCustomer() { return customer; }
    public List<Seat> getSeats() { return seats; }
    public double calculateTotal() {
        double total = 0;
        for (Seat s : seats) total += s.getPrice();
        return total;
    }
}
class TicketSystem {
    public Booking createBooking(Customer customer, Show show, List<Seat> seats) {
        if (seats.size() > 6) {
            System.out.println("Cannot book more than 6 seats.");
            return null;
        }
        for (Seat s : seats) {
            if (show.isSeatBooked(s.getSeatNumber())) {
                System.out.println("Seat " + s.getSeatNumber() + " is already booked for this show.");
                return null;
            }
        }
        List<String> seatNums = new ArrayList<>();
        for (Seat s : seats) {
            show.bookSeat(s.getSeatNumber());
            seatNums.add(s.getSeatNumber());
        }
        Booking booking = new Booking(customer, show, seats);
        System.out.printf("Booking confirmed for %s: %s. Total: %.2f.%n",
                customer.getName(), String.join(", ", seatNums), booking.calculateTotal());
        return booking;
    }
    public void cancelBooking(Booking booking, Show show, boolean isBeforeShow) {
        if (!isBeforeShow) {
            System.out.println("Cannot cancel booking after show starts.");
            return;
        }
        List<String> seatNums = new ArrayList<>();
        for (Seat s : booking.getSeats()) {
            show.releaseSeat(s.getSeatNumber());
            seatNums.add(s.getSeatNumber());
        }
        System.out.println(booking.getCustomer().getName() + "'s booking cancelled. Seats " + String.join(", ", seatNums) + " released.");
    }
}
public class exp3 {
    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();
        Show show7PM = new Show("7 PM");
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");
        Booking ashaBooking = system.createBooking(asha, show7PM, Arrays.asList(
                new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5")
        ));
        system.createBooking(ravi, show7PM, Arrays.asList(new RegularSeat("A2")));
        Booking raviBooking = system.createBooking(ravi, show7PM, Arrays.asList(new ReclinerSeat("R1")));
        if (ashaBooking != null) {
            system.cancelBooking(ashaBooking, show7PM, true);
        }
        system.createBooking(neha, show7PM, Arrays.asList(new RegularSeat("A2")));
    }
}