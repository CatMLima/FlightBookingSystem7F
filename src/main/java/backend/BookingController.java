package backend;

import java.util.*;

public class BookingController {

    Booking[] bookings;

    int nextBookingId = 0;

    private BookingDB db;

    static final int KR_PER_MIN = 300;
    static final int BAG_COST = 4000;
    // might want to use UUIDs for this or find a way to ensure there are no collisions

    public BookingController(BookingDB bookingDB){
        db = bookingDB;
    }

    public int calculatePrice(Flight f, int bags) {
        return (f.getDuration() * KR_PER_MIN) + (bags * BAG_COST);
    }

    public ArrayList<Booking> searchBooking(Booking booking){
        return null;
    }

    public void book(Flight f, Seat s, Passenger p, int bags) {
        Booking b = new Booking(f, s, p, nextBookingId, false, bags);
        db.insert(b);
    }



}
