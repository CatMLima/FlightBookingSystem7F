package catlima.todatabasefrominterfacetest;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import backend.Booking;
import backend.BookingDB;
import backend.Flight;
import backend.Passenger;
import backend.Seat;

public class BookingController {

    ArrayList<Booking> bookings = new ArrayList<Booking>();
    BookingDB db = new BookingDB();
    // Might want to move this later
    static final int KR_PER_MIN = 300;
    static final int BAG_COST = 4000;
    // might want to use UUIDs for this or find a way to ensure there are no collisions
    int nextBookingId = 0;

    public ArrayList<Booking> searchBookings() {
        return null;
    }

    public void book(Flight f, Seat s, Passenger p, int bags) {
        Booking b = new Booking(f, s, p, nextBookingId, false, bags);
        db.insert(b);
    }

    public void cancel(Booking b) {
        db.delete(b);
    }

    public int calculatePrice(Flight f, int bags) {
        return (f.getDuration() * KR_PER_MIN) + (bags * BAG_COST);
    }

    public void onReturn(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.SEARCH, true);
    }
}
