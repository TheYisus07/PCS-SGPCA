package bussisslogic;

import businesslogic.WorkplanDAO;
import domain.Objective;
import domain.Strategy;
import domain.Workplan;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author daniCV
 */

public class WorkplanDAOTest {
   
    @Test
    public void testAddWorkPlanSuccessfully() {
        Date startDate = new Date((2022-1900), (4-1), 12);
        Date finishDate = new Date((2023-1900), (4-1), 12);
        Workplan workplan = new Workplan("P_LIS_UVistCA", startDate, finishDate, "Angel Juan Sanchez García");
        WorkplanDAO workplanDAO = new WorkplanDAO();
        int successfulSave = workplanDAO.addWorkPlan(workplan);
        Assert.assertEquals(1, successfulSave);
    }
    
    @Test
    public void testConsultWorkplanByKeyCode() {
        WorkplanDAO workplan = new WorkplanDAO();
        Workplan workplanResult;
        String workplanKeyCodeExpected = "P_LIS_UVistCA";
        workplanResult = workplan.consultWorkplanByKeyCode(workplanKeyCodeExpected);
        String workplanlanKeycodeActual = workplanResult.getKeyCode();
        Assert.assertEquals("Prueba obtener otro Plan de trabajo", workplanKeyCodeExpected, workplanlanKeycodeActual);
    }
    
    @Test
    public void testUpdateWorkplanSuccessfully() {
        Date startDate = new Date((2023-1900), (10-1), 1);
        Date finishDate = new Date((2024-1900), (10-1), 1);
        WorkplanDAO workplan = new WorkplanDAO();
        Workplan updatedWorkplan = new Workplan("P_LIS_UVistCA", startDate, finishDate, "Angel Juan Sanchez García");
        int updatedSuccessful = workplan.manageWorkPlan(updatedWorkplan);
        Assert.assertEquals(1, updatedSuccessful);
    }
    
    @Test
    public void testAddObjetiveSuccessfully() {
        Objective objective = new Objective("Aumentar el numero de integrantes del CA", "Mediante las juntas del CA se acordó colegiadamente la necesidad de integrar un nuevo miembro en el CA que cuente con el titulo de doctor", "Pendiente", "P_LIS_UVistCA");
        WorkplanDAO workplanDAO = new WorkplanDAO();
        int successfulSave = workplanDAO.addObjetive(objective);
        Assert.assertEquals(1, successfulSave);
    }
    
    @Test
    public void testAddStrategySuccessfully() {
        Strategy strategy = new Strategy(01, "Obtener la acreditación y asignación de un nuevo integrante del CA mediante su alta en PRODEP y en el sistema PLATA UV", "Conocer los programas de intercambio docente con otras universidades o facultades de área técnica", "Desarrollo de filtro para docencia de tiempo completo", "Incorpotación de 1 Integrante para septiembre de 2021", 2);
        WorkplanDAO workplanDAO = new WorkplanDAO();
        int successfulSave = workplanDAO.addStrategy(strategy);
        Assert.assertEquals(1, successfulSave);
    }
    
    @Test
    public void testUpdatemodifyObjectiveSuccessfully() {
        Objective objective = new Objective("Aumentar el numero de integrantes del CA", "Mediante las juntas del CA se acordó colegiadamente la necesidad de integrar un nuevo miembro en el CA que cuente con el titulo de doctor", "Completado", "P_LIS_UVistCA");
        WorkplanDAO workplanDAO = new WorkplanDAO();
        int successfulSave = workplanDAO.modifyObjective(objective, 2);
        Assert.assertEquals(1, successfulSave);
    }
    
    @Test
    public void testconsultListOfWorkplans() {
        ArrayList<Workplan> listWorkplans = new ArrayList<>();
        WorkplanDAO workplan = new WorkplanDAO();
        listWorkplans = workplan.consultListOfWorkplans();
        Assert.assertTrue(!listWorkplans.isEmpty());
    }
    
    @Test
    public void testDeleteStrategyByGoalId() {
        WorkplanDAO workplan = new WorkplanDAO();
        int deleteResult = workplan.deleteStrategyByObjectiveId(1, 2);
        Assert.assertEquals(1, deleteResult);
    }
    
    @Test
    public void testConsultListOfObjectives() {
        ArrayList<Objective> listObjectives = new ArrayList<>();
        WorkplanDAO workplan = new WorkplanDAO();
        listObjectives = workplan.consultListOfObjectives();
        Assert.assertTrue(!listObjectives.isEmpty());
    }
    
    @Test
    public void testconsultListOfStrategys() {
        ArrayList<Strategy> listStrategys = new ArrayList<>();
        WorkplanDAO workplan = new WorkplanDAO();
        listStrategys = workplan.consulListOfStrategys();
        Assert.assertTrue(!listStrategys.isEmpty());
    }
}
 