module catlima.todatabasefrominterfacetest {
    requires javafx.controls;
    requires javafx.fxml;


    opens catlima.todatabasefrominterfacetest to javafx.fxml;
    exports catlima.todatabasefrominterfacetest;
}