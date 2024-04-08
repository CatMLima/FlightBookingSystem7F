package catlima.todatabasefrominterfacetest;

import backend.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class FlightSearchUI {

    @FXML
    private ListView<Flight> fxFlightList;

    @FXML
    private Label fxPricesFrom;

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

    @FXML
    private TextField fxSelectedPrice;

    @FXML
    private Label fxBookingStatus;

    private FlightController flightController;

    private BookingController bookingController;

    //Anything that needs to somehow be initialized should go in here.
    public void initialize() throws ClassNotFoundException {

        flightController = new FlightController(new FlightDB());
        bookingController = new BookingController(new BookingDB());
        fxDepartureDate.setValue(LocalDate.now());
        populateDropDown();
        createBindings();
    }


    // Adds the names of all the airports in the database onto the drop-down choices to avoid hard-coding.
    public void populateDropDown(){
        airportNames = flightController.getAirportNames();
        ObservableList<String> airportsObs = FXCollections.observableArrayList(airportNames);
        fxLocationPick.setItems(airportsObs);
        fxDestinationPick.setItems(airportsObs);
    }

    // This method should make sure that a user cannot click Book without first selecting a flight to view.
    public void createBindings(){
        fxFlightList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observableValue, Flight flight, Flight t1) {
                fxPricesFrom.setText(String.valueOf(t1.getDuration()*300));
            }
        });
    }


    /*
    Will extract the values from the UI and sent them to the Flight controller class to be processed.
     */
    @FXML
    protected void onSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, ParseException {
        String location = fxLocationPick.getValue();
        String destination = fxDestinationPick.getValue();
        String date = String.valueOf(fxDepartureDate.getValue());

        if (location != null && fxSelectedPrice.getText().isEmpty()){
            flightArrayList = flightController.searchFlights(location, destination, date);
        } else {
            flightArrayList = flightController.searchFlightsWithPrice(location, destination, date, Integer.parseInt(fxSelectedPrice.getText()));
        }
        ObservableList<Flight> testFlightObs = FXCollections.observableArrayList(flightArrayList);
        fxFlightList.setItems(testFlightObs);

    }

    @FXML
    protected void onBook(ActionEvent actionEvent) throws IOException {

        Flight selected = fxFlightList.getSelectionModel().getSelectedItem();

        if (selected != null){

            BookingDialog bookingDialog = new BookingDialog(fxFlightList.getSelectionModel().getSelectedItem());
            Optional<String []> result = bookingDialog.showAndWait();

            if(result.isPresent()){
                String [] answer = result.get();

                if (!answer[0].equals("none")){
                    Passenger passenger = new Passenger(answer[0], Integer.parseInt(answer[1]), answer[2], answer[3]);
                    bookingController.book(selected, selected.getSeat(answer[4]), passenger, Integer.parseInt(answer[5]));
                    fxBookingStatus.setText("Booking Complete");


                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Confirmation");
                    a.setHeaderText("Booking confirmed.");
                    a.setContentText("Your booking has been finished.");
                    a.showAndWait();

                }
            }

        }

    }

    public void resetUI(){
        fxDepartureDate.setValue(null);
        fxLocationPick.setValue(null);
        fxDestinationPick.setValue(null);
        fxSelectedPrice.setText(null);
    }

    public void onAdmin(ActionEvent actionEvent) {
        try {
            // Load the flight adding FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("flight-control.fxml"));
            Parent root = loader.load();

            // Show the booking view
            Scene scene = new Scene(root);
            Stage stage = (Stage) fxFlightList.getScene().getWindow(); // Assuming listView is part of your current scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
