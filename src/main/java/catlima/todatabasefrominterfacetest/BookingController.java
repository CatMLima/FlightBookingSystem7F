package catlima.todatabasefrominterfacetest;

import javafx.event.ActionEvent;

public class BookingController {

    public void onReturn(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.SEARCH, true);
    }
}
