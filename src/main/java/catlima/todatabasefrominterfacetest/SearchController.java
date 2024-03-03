package catlima.todatabasefrominterfacetest;

import backend.DataExchange;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchController {

    @FXML
    private DatePicker fxDepartureDate;

    /* Add a binding for the Button fxSearch so they cannot search without selecting their location
    * and destination. */
    @FXML
    private Button fxSearch;

    @FXML
    private ListView<String> fxFlightsView;

    @FXML
    private ChoiceBox<String> fxLocationPick;

    @FXML
    private ChoiceBox<String> fxDestinationPick;

    private ArrayList<String> flightsList = new ArrayList<>();

    private ArrayList<String> airportNames = new ArrayList<>();

    //Anything that needs to somehow be initialized should go in here.
    public void initialize() throws ClassNotFoundException {
        fxDepartureDate.setValue(LocalDate.now());
        DataExchange.initialize();
        populateDropDown();
    }


    // Adds the names of all the airports in the database onto the drop-down choices to avoid hard-coding.
    public void populateDropDown(){
        airportNames = DataExchange.dbAirportNames();
        ObservableList<String> airportsObs = FXCollections.observableArrayList(airportNames);
        fxLocationPick.setItems(airportsObs);
        fxDestinationPick.setItems(airportsObs);
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
            flightsList = DataExchange.dbSearch(location,destination,date);
        }
        ObservableList<String> flightsObs = FXCollections.observableArrayList(flightsList);
        fxFlightsView.setItems(flightsObs);

    }

    @FXML
    protected void onCancelClick(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.HOME, true);
    }

    public void onAdmin(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.FLIGHT, true);
    }
}
