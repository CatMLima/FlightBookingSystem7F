package catlima.todatabasefrominterfacetest;

import backend.FlightController;
import backend.FlightDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlightAddingUI {

    /*
    This class is responsible for creating new flights. All of their seats will be available to begin with and can later be populated.
     */

    //REMEMBER: to bind the button fxCreateButton's ability to be clicked to all the fields being filled out and date picked.

    //Airport Names:

    public TextField fxFlightID;
    public ChoiceBox<String> fxLocationChoices;
    public ChoiceBox<String> fxDestinationChoices;
    public DatePicker fxDepartureDate;

    // public DatePicker fxArrivalDate;
    public TextField fxDepartureTime;
    public TextField fxArrivalTime;
    public Button fxCreateButton;
    public Label fxConfirmation;

    FlightController flightController;
    FlightDB flightDB;

    public void initialize() throws ClassNotFoundException {
        flightDB = new FlightDB();
        populateChoiceBoxes();
        createButtonBinding();
        createTextBinding();
        flightController = new FlightController(flightDB);

    }

    //Add a binding here so the Add Flight Button can't be clicked until the person has put in all the
    // necessary information.
    public void createButtonBinding(){

    }

    public void createTextBinding(){
        fxFlightID.textProperty().addListener((observable, oldValue, newValue)->{
            if (!newValue.isEmpty()){
                fxConfirmation.setText("");
            }
        });

    }

    public void populateChoiceBoxes(){
        ArrayList<String> airportLocations = flightController.getAirportNames();
        ObservableList<String> airportsObs = FXCollections.observableArrayList(airportLocations);
        fxLocationChoices.setItems(airportsObs);
        fxDestinationChoices.setItems(airportsObs);
    }

    public void onCreateFlight(ActionEvent actionEvent) throws SQLException {
        flightDB.create(fxFlightID.getText(),fxLocationChoices.getValue(),fxDestinationChoices.getValue(),String.valueOf(fxDepartureDate.getValue()),
                fxDepartureTime.getText(),String.valueOf(fxDepartureDate.getValue()),fxArrivalTime.getText());
        clearData();
    }

    public void clearData(){
        fxConfirmation.setText("Flight Added");
        fxFlightID.clear();
        /*
        fxDestinationChoices.setValue(null);
        fxLocationChoices.setValue(null);
        fxDepartureDate.setValue(null);
        fxDepartureTime.clear();
        fxArrivalTime.clear();
         */
    }

    public void onReturn(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SEARCH, true);
    }
}
