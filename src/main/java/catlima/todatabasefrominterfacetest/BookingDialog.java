package catlima.todatabasefrominterfacetest;

import backend.Booking;
import backend.Flight;
import backend.Passenger;
import backend.Seat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BookingDialog extends Dialog<String[]> {

    @FXML
    Button fxFinishBooking;

    @FXML
    Button fxCancel;

    @FXML
    DatePicker fxDateOfBirth;

    @FXML
    TextField fxPassengerName;

    @FXML
    TextField fxEmail;

    @FXML
    TextField fxPhoneNumber;

    @FXML
    TextField fxAddress;

    @FXML
    TextField fxPassportNumber;

    @FXML
    TextField fxLuggageNumber;

    @FXML
    Label fxCurrentFlight;

    @FXML
    Label fxAvailableSeats;

    @FXML
    ChoiceBox<Seat> fxSeatChoice;

    private Flight flight;


    public BookingDialog(Flight flight){
        this.flight = flight;
        setDialogPane(readBookingDialog());
        fxCurrentFlight.setText(flight.getId());
        getAvailableSeats(flight.getSeats());
        createButtonBindings();

        //fxFinishBooking.getProperties().put("buttonType", "FINISH");
        //fxCancel.getProperties().put("buttonType", "CANCEL");
    }

    private void createButtonBindings() {

    }

    private void getAvailableSeats(ArrayList<Seat> seats){
        int count = 0;
        for (Seat seat : seats) {
            if (!seat.getBooked()) {
                if (count % 10 == 0) {
                    fxAvailableSeats.setText(fxAvailableSeats.getText() + "\n");
                }
                fxAvailableSeats.setText(fxAvailableSeats.getText() + " " + seat.getSeatName());
                count++;
            }
        }
        ObservableList<Seat> availableSeatsList = FXCollections.observableArrayList(seats);
        fxSeatChoice.setItems(availableSeatsList);
    }

    /*
    Can change how Booking numbers are created later.
     */

    @FXML
    public void onFinishBooking(ActionEvent actionEvent){
        //Passenger passenger = new Passenger(fxPassengerName.getText(), Integer.parseInt(fxPassportNumber.getText()),
                //fxAddress.getText(), fxPhoneNumber.getText());
        //Booking booking = new Booking(flight, fxSeatChoice.getValue(), passenger, 1, true, Integer.parseInt(fxLuggageNumber.getText()));
        String [] result = {fxPassengerName.getText(), fxPassportNumber.getText(), fxAddress.getText(), fxPhoneNumber.getText(), fxSeatChoice.getValue().getSeatName(), fxLuggageNumber.getText()};
        setResult(result);


    }

    @FXML
    public void onCancel(ActionEvent actionEvent){
        //setResult(new Booking(flight,new Seat("no", false, false),new Passenger("no", 0,"no","no"),0,false,0));
        String [] result = {"none"};
        setResult(result);

    }

    private boolean checkAllFilled(){
        return (fxAddress.getText().isEmpty() && fxPassengerName.getText().isEmpty() &&
                fxEmail.getText().isEmpty() && fxPassportNumber.getText().isEmpty());
    }

    private DialogPane readBookingDialog() {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("booking-dialog-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception){
            System.out.println("Problems loading booking-dialog-view.fxml");
            throw new RuntimeException(exception);
        }
    }

}
