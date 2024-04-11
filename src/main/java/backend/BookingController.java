package backend;

import java.util.*;

public class BookingController {

    Booking[] bookings;

    int nextBookingId = 0;

    private BookingDB db;

    static final int KR_PER_MIN = 300;
    static final int BAG_COST = 4000;

    public BookingController(BookingDB bookingDB){
        db = bookingDB;
    }

    /*
    This method could be used if there is a desire to display the price of the booking
    while the user is booking it.
     */
    /*
    public int calculatePrice(Flight f, int bags) {
        return (f.getDuration() * KR_PER_MIN) + (bags * BAG_COST);
    }
     */


    /*
    This method could be used if an employee needs to search a booking of a customer.
     */
    public ArrayList<Booking> searchBooking(Booking booking){
        return null;
    }

    /*
    Most important part of this controller. It makes sure to update the relevant objects
    and call the responsible db class.
     */
    public void book(Flight f, Seat s, Passenger p, int bags) {
        Booking b = new Booking(f, s, p, nextBookingId, false, bags);
        f.getSeat(s.getSeatName()).setBooked(true);
        db.insert(b);
    }



}
