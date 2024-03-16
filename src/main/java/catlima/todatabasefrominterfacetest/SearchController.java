package catlima.todatabasefrominterfacetest;

import backend.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchController {

    @FXML
    private ListView<Flight> fxFlightList;

    private ArrayList<Flight> flightArrayList = new ArrayList<>();

    @FXML
    private DatePicker fxDepartureDate;

    /* Add a binding for the Button fxSearch so they cannot search without selecting their location
    * and destination. */
    @FXML
    private Button fxSearch;

    @FXML
    private ChoiceBox<String> fxLocationPick;

    @FXML
    private ChoiceBox<String> fxDestinationPick;

    private ArrayList<String> airportNames = new ArrayList<>();

    @FXML
    private Button fxBookButton;

    //Anything that needs to somehow be initialized should go in here.
    public void initialize() throws ClassNotFoundException {
        fxDepartureDate.setValue(LocalDate.now());
        FlightDB.initialize();
        AirportDB.initialize();
        BookingDB.initialize();
        SeatDB.initialize();
        PassengerDB.initialize();
        populateDropDown();
        createBindings();
    }


    // Adds the names of all the airports in the database onto the drop-down choices to avoid hard-coding.
    public void populateDropDown(){
        airportNames = AirportDB.dbAirportNames();
        ObservableList<String> airportsObs = FXCollections.observableArrayList(airportNames);
        fxLocationPick.setItems(airportsObs);
        fxDestinationPick.setItems(airportsObs);
    }

    // This method should make sure that a user cannot click Book without first selecting a flight to view.
    public void createBindings(){

    }


    /*
    TO TEST IT: Pick the Reykjavik Domestic Airport (RKV), Vatnsmýri, 101 Reykjavík as a location and the Urðargill 15, 600 Akureyri as destination and the date 6/6/2024
     */
    @FXML
    protected void onSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String location = fxLocationPick.getValue();
        String destination = fxDestinationPick.getValue();
        String date = String.valueOf(fxDepartureDate.getValue());

        if (location != null){
            flightArrayList = FlightDB.dbFlightSearch(location, destination, date);
        }
        ObservableList<Flight> testFlightObs = FXCollections.observableArrayList(flightArrayList);
        fxFlightList.setItems(testFlightObs);

    }

    @FXML
    protected void onBook(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.BOOKING, true);
    }

    public void onAdmin(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.FLIGHT, true);
    }
}
