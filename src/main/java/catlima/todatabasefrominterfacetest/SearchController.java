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
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchController {

    @FXML
    private TextField fxLocation;

    @FXML
    private TextField fxDate;

    @FXML
    private TextField fxDestination;

    @FXML
    private DatePicker fxDepartureDate;

    /* Add a binding for the Button fxSearch so they cannot search without selecting their location
    * and destination. */

    @FXML
    private Button fxSearch;


    public void initialize(){ fxDepartureDate.setValue(LocalDate.now());}

    private ArrayList<String> flightsList = new ArrayList<>();

    @FXML
    private ListView<String> fxFlightsView;


    /*
    TO TEST IT: Pick the Reykjavik Domestic Airport (RKV), Vatnsmýri, 101 Reykjavík as a location and the Urðargill 15, 600 Akureyri as destination and the date 6/6/2024
     */
    @FXML
    protected void onSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String location = fxLocation.getText();
        String date = String.valueOf(fxDepartureDate.getValue());
        String destination =fxDestination.getText();

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

}
