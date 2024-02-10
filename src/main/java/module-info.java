module catlima.todatabasefrominterfacetest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;


    opens catlima.todatabasefrominterfacetest to javafx.fxml;
    exports catlima.todatabasefrominterfacetest;
}