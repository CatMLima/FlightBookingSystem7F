package catlima.todatabasefrominterfacetest;

import backend.DataExchange;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchController {

    @FXML
    private TextField fxLocation;

    @FXML
    private TextField fxDate;

    @FXML
    private DatePicker fxDepartureDate;

    private String selectedDate;


    public void initialize(){
        fxDepartureDate.setValue(LocalDate.now());
    }

    @FXML
    protected void onSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String location = fxLocation.getText();
        String date = String.valueOf(fxDepartureDate.getValue());

        // put "date" as second parameter.
        if (location != null){
            DataExchange.dbSearch(location,date);
        }

    }

    @FXML
    protected void onCancelClick(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.HOME, true);
    }

}
