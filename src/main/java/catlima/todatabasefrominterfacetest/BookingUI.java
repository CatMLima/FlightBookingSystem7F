package catlima.todatabasefrominterfacetest;

import backend.*;
import javafx.event.ActionEvent;

import java.io.*;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class BookingUI {

    @FXML
    private Label fxCurrentFlight;

    private Flight selectedFlight;

    @FXML
    private GridPane fxSeatsGrid;

    private FlightController flightController;

    public void initialize() throws ClassNotFoundException {
        flightController = new FlightController(new FlightDB());
        //populateSeatsGrid();

    }

    Seat [][] seats;

    public void populateSeatsGrid(){
        seats = flightController.getSeatAvailability(selectedFlight);

        for (int row = 0; row < seats.length; row++){
            for (int col = 0; col < seats[row].length; col++){
                Button seatButton = new Button("Seat " + seats[row][col].getSeatName());
                seatButton.setPrefSize(80,40);
                // can add condition if the seat is on hold here.
                if (seats[row][col].getBooked()){
                    seatButton.setStyle("-fx-background-color: red;");
                } else {
                    seatButton.setStyle("-fx-background-color: green;");
                }

                int finalRow = row;
                int finalCol = col;

                seatButton.setOnAction(e -> {
                    seatButton.setStyle("-fx-background-colo: yellow;");
                });
            }
        }
    }


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
