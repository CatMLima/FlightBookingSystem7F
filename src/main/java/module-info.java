module catlima.todatabasefrominterfacetest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens catlima.todatabasefrominterfacetest to javafx.fxml;
    exports catlima.todatabasefrominterfacetest;
    exports backend;
    exports testingObjects;
}

