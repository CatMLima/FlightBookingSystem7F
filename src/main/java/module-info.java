module catlima.todatabasefrominterfacetest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;




    opens catlima.todatabasefrominterfacetest to javafx.fxml;
    exports catlima.todatabasefrominterfacetest;
}