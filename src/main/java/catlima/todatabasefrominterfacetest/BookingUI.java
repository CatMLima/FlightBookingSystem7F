package catlima.todatabasefrominterfacetest;

import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;

import backend.Booking;
import backend.BookingDB;
import backend.Flight;
import backend.Passenger;
import backend.Seat;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.*;

public class BookingUI {

    @FXML
    private Label fxCurrentFlight;

    private Flight selectedFlight;

    public void onReturn(ActionEvent actionEvent){

        //ViewSwitcher.switchTo(View.SEARCH, true);

        try {
            // Load the booking FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search-view.fxml"));
            Parent root = loader.load();

            // Show the booking view
            Scene scene = new Scene(root);
            Stage stage = (Stage) fxCurrentFlight.getScene().getWindow(); // Assuming listView is part of your current scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
        fxCurrentFlight.setText(this.selectedFlight.getId());
    }
}
