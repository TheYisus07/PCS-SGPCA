package gui;

import businesslogic.MemberDAO;
import businesslogic.WorkplanDAO;
import domain.Objective;
import domain.Workplan;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private double x = 0;
    private double y = 0;
    
    @FXML
    private AnchorPane anchorPaneLateralMenu;
    @FXML
    private AnchorPane anchorPaneMainWorplan;
    @FXML
    private AnchorPane anchorPaneManageWorkplan;
    @FXML
    private AnchorPane anchorPaneWhiteWorkplan;
    @FXML
    private Button buttonManageWorkplan;
    @FXML
    private Button buttonExitWorkplanManage;
    @FXML
    private ComboBox<String> comboBoxWorkplanNames;
    @FXML
    private ComboBox<String> comboBoxObjectivesArchieved;
    @FXML
    private DatePicker datePickerManagedWorkplanStartDate;
    @FXML
    private DatePicker datePickerManagedWorkplanFinishDate;
    @FXML
    private ImageView imagenViewMainLateralMenu;
    @FXML
    private ImageView imagenViewMainBackLateralMenu;
    @FXML
    private Label labelAcademicGroup;
    @FXML
    private Label labelMemberName;
    @FXML
    private Label labelMemberEmail;
    @FXML
    private Label labelMemberPosition;
    @FXML
    private TableView<Objective> tableViewObjectives;
    @FXML
    private TableColumn tableColumnObjectiveNames;
    @FXML
    private TableView<Objective> tableViewAllObjectives;
    @FXML
    private TableColumn tableColumnAllObjectiveTitles;
    @FXML
    private TableColumn tableColumnAllObjectiveStatus;
    @FXML
    private TextField textFieldManagedWorkplanKeycode;
    @FXML
    private ImageView bote;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBoxWithWorkplanNames();
        mainLateralMenu();
        selectedPendingObjective();
    }  
    
    @FXML 
    public void event(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLConsultEventHistory.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
    public void fillComboBoxWithWorkplanNames() { 
        ObservableList<String> workplanList = FXCollections.observableArrayList();
        for (int i = 0; i < workplanDAO.consultListOfWorkplans().size(); i++) {
            String workplanName = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            String workplanStartDate = Integer.toString(workplanDAO.consultListOfWorkplans().get(i).getStartDate().getYear() + 1900);
            String workplanFinishDate = Integer.toString(workplanDAO.consultListOfWorkplans().get(i).getFinishDate().getYear() + 1900);
            String comboBoxInfo = workplanName + " [" + workplanStartDate + "-" + workplanFinishDate + "]"; 
            workplanList.add(comboBoxInfo);
        }    
        comboBoxWorkplanNames.setItems(workplanList); 
    }
    
    public String getWorkplanKeycode() {
        String comboBoxInfo = comboBoxWorkplanNames.getSelectionModel().getSelectedItem();
        String workplanKeycode = "";
        if (comboBoxInfo.contains("[")) {
            String[] parts = comboBoxInfo.split("\\[");
            workplanKeycode = parts[0].trim();
        }
        return workplanKeycode;
    }
    
    @FXML
    public void selectComboBoxWorkplanKeycodes(ActionEvent actionEvent) {
        tableViewObjectives.getItems().clear();
        String workplanKeycode = getWorkplanKeycode();
        for (int i = 0; i < workplanDAO.consultListOfWorkplans().size(); i++) {
            String expectedWorplanName = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            if (workplanKeycode.equals(expectedWorplanName)) {
                String memberName = workplanDAO.consultListOfWorkplans().get(i).getMemberFullName();
                responsibleForTheWorkplan(memberName);
                pendingObjectivesOfTheSelectedWorkplan(workplanKeycode);
                archievedObjectivesTheSelectedWorkplan(workplanKeycode);
            }
        }
    }
    
    public void responsibleForTheWorkplan(String memberName) {
        for (int i = 0; i < memberDAO.consultMemberList().size(); i++) {
            String expectedMemberName = memberDAO.consultMemberList().get(i).getFullName();
            if (memberName.equals(expectedMemberName)) {
                String memberAcademicGroup = memberDAO.consultMemberList().get(i).getKeycodeAcademicGroup();
                String memberInstitutionalMail = memberDAO.consultMemberList().get(i).getInstitutionalMail();
                String memberPosition = memberDAO.consultMemberList().get(i).getPosition();
                labelMemberName.setText(memberName);
                labelAcademicGroup.setText(memberAcademicGroup);
                labelMemberEmail.setText(memberInstitutionalMail);
                labelMemberPosition.setText(memberPosition);
            }
        }
    }
    
    public void pendingObjectivesOfTheSelectedWorkplan(String workplanName) { 
        ObservableList<Objective> objectivePendingList = FXCollections.observableArrayList();
        String statusPending = "Pendiente";
        for (int i = 0; i < workplanDAO.consultListOfObjectives().size(); i++) {
            boolean isPendingObjective = statusPending.equals(workplanDAO.consultListOfObjectives().get(i).getStatus());
            if (workplanName.equals(workplanDAO.consultListOfObjectives().get(i).getWorkplanKeyCode()) && isPendingObjective) {
                objectivePendingList.add(workplanDAO.consultListOfObjectives().get(i));
            }
        }
        tableColumnObjectiveNames.setCellValueFactory(new PropertyValueFactory("Title"));
        tableViewObjectives.setItems(objectivePendingList);
    }
    
    public void archievedObjectivesTheSelectedWorkplan(String workplanName) {
        ObservableList<String> objectiveArchivedList = FXCollections.observableArrayList();
        String statusArchieved = "Cumplido";
        for (int i = 0; i < workplanDAO.consultListOfObjectives().size(); i++) {
            boolean isArchievedObjective = statusArchieved.equals(workplanDAO.consultListOfObjectives().get(i).getStatus());
            if (workplanName.equals(workplanDAO.consultListOfObjectives().get(i).getWorkplanKeyCode()) && isArchievedObjective) {
                objectiveArchivedList.add(workplanDAO.consultListOfObjectives().get(i).getTitle());
            }
        }
        if (objectiveArchivedList.isEmpty()) {
            objectiveArchivedList.add("Sin objetivos Cumplidos");
        }
        comboBoxObjectivesArchieved.setItems(objectiveArchivedList);
    }
    
    public void hideLateralMenu() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), anchorPaneWhiteWorkplan);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
            
        fadeTransition1.setOnFinished(event1 -> {
            anchorPaneWhiteWorkplan.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), anchorPaneLateralMenu);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }
    
    public void showLateralMenu() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), anchorPaneWhiteWorkplan);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.15);
        fadeTransition1.play();
            
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), anchorPaneLateralMenu);
        translateTransition1.setByX(+600);
        translateTransition1.play();
    }
    
    public void mainLateralMenu() {
        anchorPaneWhiteWorkplan.setVisible(false);
        hideLateralMenu();

        imagenViewMainLateralMenu.setOnMouseClicked(event -> {       
            anchorPaneWhiteWorkplan.setVisible(true);
            imagenViewMainLateralMenu.setVisible(false);
            showLateralMenu();
        });
        
        anchorPaneWhiteWorkplan.setOnMouseClicked(event -> {
            hideLateralMenu();
            imagenViewMainLateralMenu.setVisible(true);
        });
        
        imagenViewMainBackLateralMenu.setOnMouseClicked(event -> {
            hideLateralMenu();
            imagenViewMainLateralMenu.setVisible(true);
        });
    }
    
    public String getObjectiveTitleSelected() {
        String objectiveTitle = "";
        boolean notSelectedTableView = tableViewObjectives.getSelectionModel().isCellSelectionEnabled();
        if (notSelectedTableView) {
            objectiveTitle = tableViewObjectives.getSelectionModel().getSelectedItem().getTitle();
        } else {
            objectiveTitle = comboBoxObjectivesArchieved.getSelectionModel().getSelectedItem();
        } 
        return objectiveTitle;
    }
    
    public void showGuiObjective() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("FXMLObjective.fxml"));
            fXMLLoader.load();
            ControllerObjective controllerObjective = fXMLLoader.getController();
            String objectiveTitle = getObjectiveTitleSelected();
            String workplanKeycode = getWorkplanKeycode().trim();
            System.out.println(objectiveTitle + " y " + workplanKeycode);
            controllerObjective.getObjectiveTitleSelected(objectiveTitle, workplanKeycode);
            Parent root = fXMLLoader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED); 
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });        
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControllerWorkplan.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void selectedPendingObjective() {            
        tableViewObjectives.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Objective>() {
            @Override
            public void changed(ObservableValue<? extends Objective> observable, Objective oldObjective, Objective newObjective) {
                if (tableViewObjectives.getSelectionModel().getSelectedItem() != null) {
                    showGuiObjective();
                }
            }
        });
    }
    
    @FXML
    public void selectedComboBoxObjectivesArchieved(ActionEvent actionEvent) {
        String objectiveTitle = comboBoxObjectivesArchieved.getPromptText();
        if (comboBoxObjectivesArchieved.getSelectionModel().selectedItemProperty() != null) {
            showGuiObjective();
        }
    }
    
    @FXML
    public void clear(MouseEvent mouseEvent) {
        tableViewObjectives.getSelectionModel().clearSelection();
        comboBoxObjectivesArchieved.getSelectionModel().clearSelection();
        System.out.println("hola");
    }
    
    public LocalDate convertToLocalDate(Date date) {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
    
    public Date convertToDate(LocalDate localDate){
        return java.util.Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    
    @FXML
    public void manageWorkplanSelected(ActionEvent actionEvent) {
        boolean isSelectedWorkplan = false;
        String workplanKeycode = getWorkplanKeycode();
        for (int i = 0; i < workplanDAO.consultListOfWorkplans().size(); i++) {
            String expectedWorplanKeycode = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            if (workplanKeycode.equals(expectedWorplanKeycode)) {
                isSelectedWorkplan = true;
            }
        }
        if (isSelectedWorkplan) {
            
       
        
        anchorPaneMainWorplan.setVisible(false);
        anchorPaneManageWorkplan.setVisible(true);
        String managedWorkplanKeycode = getWorkplanKeycode();
        textFieldManagedWorkplanKeycode.setText(managedWorkplanKeycode);
        for (int i = 0; i < workplanDAO.consultListOfWorkplans().size(); i++) {
            String actualWorkplanKeycode = workplanDAO.consultListOfWorkplans().get(i).getKeyCode();
            if (managedWorkplanKeycode.equals(actualWorkplanKeycode)) {
                Date starDate = new Date();
                starDate = workplanDAO.consultListOfWorkplans().get(i).getStartDate();
                Date finishDate = new Date(); 
                finishDate = workplanDAO.consultListOfWorkplans().get(i).getFinishDate();
                datePickerManagedWorkplanStartDate.setValue(convertToLocalDate(starDate));
                datePickerManagedWorkplanFinishDate.setValue(convertToLocalDate(finishDate));
            }
        }
        fillTableWithAllObjectives(managedWorkplanKeycode);
        
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Seleccione un plan de trabajo");
            alert.setTitle("Advertencia");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }
    
    public void fillTableWithAllObjectives(String workplanKeycode) {
        ObservableList<Objective> listOfAllObjectives = FXCollections.observableArrayList();
        for (int i = 0; i < workplanDAO.consultListOfObjectives().size(); i++) {
            if (workplanKeycode.equals(workplanDAO.consultListOfObjectives().get(i).getWorkplanKeyCode())) {
                listOfAllObjectives.add(workplanDAO.consultListOfObjectives().get(i));
            }
        }
        tableColumnAllObjectiveTitles.setCellValueFactory(new PropertyValueFactory("Title"));
        tableColumnAllObjectiveStatus.setCellValueFactory(new PropertyValueFactory("Status"));
        tableViewAllObjectives.setItems(listOfAllObjectives);
    }
      
    @FXML
    public void saveWorkplanAdministration(ActionEvent actionEvent) {
        String workplanKeycode = null;
        String workplanMemberName = null;
        LocalDate startDate = null;
        LocalDate finishDate = null;
        boolean isCorrectWorkplanKeyCode = !(textFieldManagedWorkplanKeycode.getText().equals("".trim()));
        boolean isCorrectWorkplanStarDate = !(datePickerManagedWorkplanStartDate.getValue() != null); 
        boolean isCorrectWorkplanFinishDate = !(datePickerManagedWorkplanStartDate.getValue() != null); 
        
        //if (isCorrectWorkplanKeyCode && isCorrectWorkplanStarDate && isCorrectWorkplanFinishDate) {
            workplanKeycode = textFieldManagedWorkplanKeycode.getText(); 
            startDate = datePickerManagedWorkplanStartDate.getValue();
            finishDate = datePickerManagedWorkplanFinishDate.getValue();
            workplanMemberName = labelMemberName.getText();
        //}
        //System.out.println(convertToDate(startDate) + " - " + convertToDate(finishDate));
        Workplan workplan = new Workplan(workplanKeycode, convertToDate(startDate), convertToDate(finishDate), workplanMemberName);
        int result = workplanDAO.manageWorkPlan(workplan);
        if (result == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("El Plan de trabajo se administro correctamente");
            alert.setTitle("Ventana de confirmación");
            alert.setContentText(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("ERROR EN LA BASE DE DATOS");
            alert.setTitle("Advertencia");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }
    
    @FXML
    public void exitWorkplanManage(ActionEvent actionEvent) {
        anchorPaneMainWorplan.setVisible(true);
        anchorPaneManageWorkplan.setVisible(false);
    }
}
