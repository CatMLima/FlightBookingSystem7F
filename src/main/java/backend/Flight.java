package backend;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

public class Flight {

        //private Airport destination;
        //private Airport source;

        private String destination;

        private String location;
        private Date date;
        private String id;
        private ArrayList<Seat> seats;
        private String status;

        // Constructor
        public Flight(String location, String destination, Date date, String id, ArrayList<Seat> seats, String status) {
            this.destination = destination;
            this.location = location;
            this.date = date;
            this.id = id;
            this.seats = seats;
            this.status = status;
        }

    @Override
    public String toString() {
        try {
            return getId() + " at " + FlightDB.fetchTime(getId(), String.valueOf(getDate()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters and Setters
        public String getDestination() {
            return this.destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Date getDate() {
            return this.date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ArrayList<Seat> getSeats() {
            return this.seats;
        }

        public void setSeats(ArrayList<Seat> seats) {
            this.seats = seats;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        // Other methods
        public ArrayList<Seat> getAvailableSeats() {
            ArrayList<Seat> availableSeats = new ArrayList<Seat>();
            for (Seat s : this.seats) {
                if (!s.getIsOnHold() && !s.getBooked()) {
                    availableSeats.add(s);
                }
            }
            return availableSeats;
        }

        // TODO: implement updateFlightStatus method
        

}

