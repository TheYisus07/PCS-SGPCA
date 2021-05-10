package gui;

import businesslogic.WorkplanDAO;
import domain.Strategy;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * @author daniCV
 */

public class ControllerObjective implements Initializable {
    private final WorkplanDAO workplanDAO = new WorkplanDAO();
    private ControllerWorkplan controllerWorkplan;
    
    @FXML
    private Label labelObjectiveTitle;
    @FXML
    private Label labelObjectiveDescription;
    @FXML
    private Button buttonCloseWindow;
    @FXML
    private TableView<Strategy> tableViewObjectiveSelected;
    @FXML
    private TableColumn tableColumnObjectiveNumber;
    @FXML
    private TableColumn tableColumnObjectiveStrategy;
    @FXML
    private TableColumn tableColumnObjectiveGoal;
    @FXML
    private TableColumn tableColumnObjectiveAction;
    @FXML
    private TableColumn tableColumnObjectiveResult;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
    public void getObjectiveTitleSelected(String objectiveTitle, String workplanKeycode) {
        int objectiveId = 0; 
        for (int i = 0; i < workplanDAO.consultListOfObjectives().size(); i++) {
            String expetectedObjectiveTitle = workplanDAO.consultListOfObjectives().get(i).getTitle();
            String expetectedWorkplanKeycode = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            if (expetectedObjectiveTitle.equals(objectiveTitle) && expetectedWorkplanKeycode.equals(workplanKeycode)) {
                objectiveId = workplanDAO.consultListOfObjectives().get(i).getObjectiveId();
                String actualObjectiveTitle = workplanDAO.consultListOfObjectives().get(i).getTitle();
                String actualObjectiveDescription = workplanDAO.consultListOfObjectives().get(i).getDescription();
                labelObjectiveTitle.setText(actualObjectiveTitle);
                labelObjectiveDescription.setText(actualObjectiveDescription);
            }
        }
        strategyTableOfTheSelectedObjective(objectiveId);
    }
    
    public void strategyTableOfTheSelectedObjective(int objectiveId) {
        ObservableList<Strategy> strategyList = FXCollections.observableArrayList();
        for (int i = 0; i < workplanDAO.consulListOfStrategys().size(); i++) {
            if (workplanDAO.consulListOfStrategys().get(i).getObjectiveID() == objectiveId) {
                strategyList.add(workplanDAO.consulListOfStrategys().get(i));
            }
        }
        tableColumnObjectiveNumber.setCellValueFactory(new PropertyValueFactory("Number"));
        tableColumnObjectiveStrategy.setCellValueFactory(new PropertyValueFactory("Description"));
        tableColumnObjectiveGoal.setCellValueFactory(new PropertyValueFactory("Goal"));
        tableColumnObjectiveAction.setCellValueFactory(new PropertyValueFactory("Action"));
        tableColumnObjectiveResult.setCellValueFactory(new PropertyValueFactory("Result"));
        tableViewObjectiveSelected.setItems(strategyList);
    }
    
    @FXML
    public void closeWindowObjective(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCloseWindow.getScene().getWindow();
        stage.close();
    }
}
