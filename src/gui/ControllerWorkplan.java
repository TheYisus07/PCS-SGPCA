package gui;

import businesslogic.MemberDAO;
import businesslogic.WorkplanDAO;
import domain.Objective;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author daniCV
 */

public class ControllerWorkplan implements Initializable {
    private final WorkplanDAO workplanDAO = new WorkplanDAO();
    private final MemberDAO memberDAO = new MemberDAO();

    @FXML
    private AnchorPane anchorPaneLateralMenu;
    @FXML
    private AnchorPane anchorPaneMainWorplan;
    @FXML
    private ComboBox<String> comboBoxWorkplanNames;
    @FXML
    private Label labelAcademicGroup;
    @FXML
    private Label labelMemberName;
    @FXML
    private Label labelMemberPosition;
    @FXML
    private TableColumn tableColumnObjectiveNames;
    @FXML
    private TableView<Objective> tableViewObjectives;
    @FXML
    private ImageView imagenViewMainLateralMenu;
    @FXML
    private ImageView imagenViewMainBackLateralMenu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBoxWithWorkplanNames();
        mainLateralMenu();
        showGuiObjective();
    }  
    
    public void fillComboBoxWithWorkplanNames() { 
        ObservableList<String> workplanList = FXCollections.observableArrayList();
        comboBoxWorkplanNames.setItems(workplanList); 
        for (int i = 0; i < workplanDAO.consultListOfWorkplans().size(); i++) {
            String workplanName = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            String workplanStartDate = Integer.toString(workplanDAO.consultListOfWorkplans().get(i).getStartDate().getYear() + 1900);
            String workplanFinishDate = Integer.toString(workplanDAO.consultListOfWorkplans().get(i).getFinishDate().getYear() + 1900);
            String comboBoxInfo = workplanName + " [" + workplanStartDate + "-" + workplanFinishDate + "]"; 
            workplanList.add(workplanName);
        }        
    }
   
    @FXML
    public void selectComboBoxWorkplanNames(ActionEvent actionEvent) {
        tableViewObjectives.getItems().clear();
        String workplanName = comboBoxWorkplanNames.getSelectionModel().getSelectedItem();
        for (int i = 0; i < workplanDAO.consultListOfWorkplans().size(); i++) {
            String expectedWorplanName = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            if (workplanName.equals(expectedWorplanName)) {
                String memberName = workplanDAO.consultListOfWorkplans().get(i).getMemberFullName();
                responsibleForTheWorkplan(memberName);
                pendingObjectivesOfTheSelectedWorkplan(workplanName);
            }
        }
    }
    
    public void responsibleForTheWorkplan(String memberName) {
        for (int i = 0; i < memberDAO.consultMemberList().size(); i++) {
            String expectedMemberName = memberDAO.consultMemberList().get(i).getFullName();
            if (memberName.equals(expectedMemberName)) {
                String memberAcademicGroup = memberDAO.consultMemberList().get(i).getKeycodeAcademicGroup();
                String memberPosition = memberDAO.consultMemberList().get(i).getPosition();
                labelMemberName.setText(memberName);
                labelAcademicGroup.setText(memberAcademicGroup);
                labelMemberPosition.setText(memberPosition);
            }
        }
    }
    
    public void pendingObjectivesOfTheSelectedWorkplan(String workplanName) { 
        ObservableList<Objective> objectivePendingList = FXCollections.observableArrayList();
        for (int i = 0; i < workplanDAO.consultListOfObjectives().size(); i++) {
            if (workplanName.equals(workplanDAO.consultListOfObjectives().get(i).getWorkplanKeyCode())) {
                objectivePendingList.add(workplanDAO.consultListOfObjectives().get(i));
            }
        }
        tableColumnObjectiveNames.setCellValueFactory(new PropertyValueFactory("Title"));
        tableViewObjectives.setItems(objectivePendingList);
    }
    
    public void hideLateralMenu() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), anchorPaneMainWorplan);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
            
        fadeTransition1.setOnFinished(event1 -> {
            anchorPaneMainWorplan.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), anchorPaneLateralMenu);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }
    
    public void showLateralMenu() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), anchorPaneMainWorplan);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.15);
        fadeTransition1.play();
            
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), anchorPaneLateralMenu);
        translateTransition1.setByX(+600);
        translateTransition1.play();
    }
    
    public void mainLateralMenu() {
        anchorPaneMainWorplan.setVisible(false);
        hideLateralMenu();

        imagenViewMainLateralMenu.setOnMouseClicked(event -> {       
            anchorPaneMainWorplan.setVisible(true);
            imagenViewMainLateralMenu.setVisible(false);
            showLateralMenu();
        });
        
        anchorPaneMainWorplan.setOnMouseClicked(event -> {
            hideLateralMenu();
            imagenViewMainLateralMenu.setVisible(true);
        });
        
        imagenViewMainBackLateralMenu.setOnMouseClicked(event -> {
            hideLateralMenu();
            imagenViewMainLateralMenu.setVisible(true);
        });
    }
    
    public void showGuiObjective() {        
        tableViewObjectives.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Objective>() {
            @Override
            public void changed(ObservableValue<? extends Objective> observable, Objective oldObjective, Objective newObjective) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLObjective.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Sistema Gestor de Productividad del Cuerpo Académico");
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show(); 
                } catch (IOException ex) {
                    Logger.getLogger(ControllerWorkplan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
