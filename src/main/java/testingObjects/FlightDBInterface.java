package testingObjects;

import backend.Flight;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface FlightDBInterface {

    void initialize() throws ClassNotFoundException;

    void closeConnection() throws SQLException;

    ArrayList<Flight> select(String location, String destination, String date) throws SQLException;

    ArrayList<Flight> selectPrice(int price) throws SQLException;

    ArrayList<Flight> selectDate(Date date) throws SQLException;

    void update(Flight flight, String depDate, String depTime, String arrDate,
                String arrTime, String status) throws SQLException, ParseException;

    void create(String flightID, String location, String destination, String depDate, String
            depTime, String arrDate, String arrTime) throws SQLException;

    void delete(Flight flight);

    String fetchID(String location) throws SQLException;






}
