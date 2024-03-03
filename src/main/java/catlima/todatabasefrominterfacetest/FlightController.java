package catlima.todatabasefrominterfacetest;

import backend.DataExchange;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlightController {

    /*
    This class is responsible for creating new flights. All of their seats will be available to begin with and can later be populated.
     */

    //REMEMBER: to bind the button fxCreateButton's ability to be clicked to all the fields being filled out and date picked.

    //Airport Names:

    public TextField fxFlightID;
    public ChoiceBox<String> fxLocationChoices;
    public ChoiceBox<String> fxDestinationChoices;
    public DatePicker fxDepartureDate;
    public DatePicker fxArrivalDate;
    public TextField fxDepartureTime;
    public TextField fxArrivalTime;
    public Button fxCreateButton;

    public void initialize(){
        populateChoiceBoxes();
        createButtonBinding();
    }

    //Add a binding here so the Add Flight Button can't be clicked until the person has put in all the
    // necessary information.
    public void createButtonBinding(){

    }

    public void populateChoiceBoxes(){
        ArrayList<String> airportLocations = DataExchange.dbAirportNames();
        ObservableList<String> airportsObs = FXCollections.observableArrayList(airportLocations);
        fxLocationChoices.setItems(airportsObs);
        fxDestinationChoices.setItems(airportsObs);
    }

    public void onCreateFlight(ActionEvent actionEvent) throws SQLException {
        DataExchange.createFlight(fxFlightID.getText(),fxLocationChoices.getValue(),fxDestinationChoices.getValue(),String.valueOf(fxDepartureDate.getValue()),
                fxDepartureTime.getText(),String.valueOf(fxArrivalDate.getValue()),fxArrivalTime.getText());
    }

    public void onReturn(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SEARCH, true);
    }
}
