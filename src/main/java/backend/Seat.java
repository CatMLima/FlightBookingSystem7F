package backend;

public class Seat {
    private String seatName;
    private String Tier;
    private Boolean isBooked;
    private Boolean isonHold;

    public Seat(String seatName, String Tier, Boolean isBooked, Boolean isonHold)   {
        this.seatName = seatName;
        this.Tier = Tier;
        this.isBooked = isBooked;
        this.isonHold = isonHold;
    }

    public Boolean getIsonHold() {
        return isonHold;
    }

    public void setIsonHold(Boolean onHold) {
        this.isonHold = onHold;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public String getTier() {
        return Tier;
    }

    public void setTier(String tier) {
        Tier = tier;
    }

    public String getSeatName() {
        return seatName;
    }
    public void setSeatName(String seatname) {
        seatName = seatName;
    }
}
