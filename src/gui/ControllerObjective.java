package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * 
 * @author daniCV
 */

public class ControllerObjective implements Initializable {
    
    private ControllerWorkplan controllerWorkplan;
    
    @FXML
    private Label labelObjectiveTitle;
    @FXML
    private Button buttonCloseWindow;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void getObjectiveTitleSelected(ControllerWorkplan controllerWorkplan, int objectiveId) {
        labelObjectiveTitle.setText(objectiveId + "");
        this.controllerWorkplan = controllerWorkplan;
    } 
    
    @FXML
    public void closeWindowObjective(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCloseWindow.getScene().getWindow();
        stage.close();
    }
}
