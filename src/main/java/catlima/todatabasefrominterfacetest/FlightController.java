package catlima.todatabasefrominterfacetest;

import javafx.event.*;
import javafx.scene.control.*;

public class FlightController {

    /*
    This class is responsible for creating new flights. All of their seats will be available to begin with and can later be populated.
     */

    //REMEMBER: to bind the button fxCreateButton's ability to be clicked to all the fields being filled out and date picked.

    public ChoiceBox fxIDChoices;
    public ChoiceBox fxLocationChoices;
    public ChoiceBox fxDestinationChoices;
    public DatePicker fxDepartureDate;
    public DatePicker fxArrivalDate;
    public TextField fxDepartureTime;
    public TextField fxArrivalTime;
    public Button fxCreateButton;

    public void onCreateFlight(ActionEvent actionEvent) {
    }

    public void onReturn(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SEARCH, true);
    }
}
