package catlima.todatabasefrominterfacetest;

import backend.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.net.*;
import java.sql.*;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField newText;

    @FXML
    private TextField fxFlightID;

    // This method will take the text in the Textfield objects and send it to the DataBaseConnection class.
    @FXML
    protected void onAddButtonClick() throws SQLException, ClassNotFoundException {

        welcomeText.setText("information inserted");
        if (!newText.getText().isBlank()) {
            DatabaseConnection.dbInsert(fxFlightID.getText(),newText.getText());
            newText.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Insert what you wanna send to the database.");
    }
}