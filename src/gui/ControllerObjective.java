package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * 
 * @author daniCV
 */

public class ControllerObjective implements Initializable {

    @FXML
    private Button buttonCloseWindow;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindowObjective();
    }
    
    @FXML
    public void closeWindowObjective() {
        buttonCloseWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}
