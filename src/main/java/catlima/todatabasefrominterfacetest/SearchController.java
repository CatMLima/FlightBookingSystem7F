package catlima.todatabasefrominterfacetest;

import backend.DataExchange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SearchController {

    @FXML
    private TextField fxLocation;

    @FXML
    private TextField fxDate;

    @FXML
    protected void onSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String location = fxLocation.getText();
        String date = fxDate.getText();

        if (location != null && date != null){
            DataExchange.dbSearch(location,date);
        }
    }

    @FXML
    protected void onCancelClick(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.HOME, true);
    }

}
