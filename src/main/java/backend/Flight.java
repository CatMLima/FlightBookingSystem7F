package backend;
import java.util.Date;
import java.util.ArrayList;

public class Flight {

        private Airport destination;
        private Airport source;
        private Date date;
        private int id;
        private ArrayList<Seat> seats;
        private String status;

        // Constructor
        public Flight(Airport destination, Airport source, Date date, int id, ArrayList<Seat> seats, String status) {
            this.destination = destination;
            this.source = source;
            this.date = date;
            this.id = id;
            this.seats = seats;
            this.status = status;
        }

        // Getters and Setters
        public Airport getDestination() {
            return this.destination;
        }

        public void setDestination(Airport destination) {
            this.destination = destination;
        }

        public Airport getSource() {
            return this.source;
        }

        public void setSource(Airport source) {
            this.source = source;
        }

        public Date getDate() {
            return this.date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
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

