package bussisslogic;

import businesslogic.BlueprintDAO;
import domain.Blueprint;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author daniCV
 */

public class BlueprintDAOTest {
    
    @Test
    public void testAddBlueprintSuccessfully() {
        Date startDate = new Date((2023-1900), (4-1), 12);
        Blueprint blueprint = new Blueprint("Teoría de los microservicios", startDate, "LGAC1, LGCA2", "Asignada", "Tesina", "Carlos Edson Romero", "Proyecto de investigación dedicado a la intervención...", "Jorge Octavio Ocharán Hernández", "Machine Learning");
        BlueprintDAO blueprintDAO = new BlueprintDAO();
        int successfulSave = blueprintDAO.addBlueprint(blueprint);
        Assert.assertEquals(1, successfulSave);
    }
    
    @Test
    public void testUpdateProjectSuccessfully() {    
        Date startDate = new Date((2024-1900), (10-1), 1);
        BlueprintDAO blueprint = new BlueprintDAO();
        Blueprint updatedBlueprint = new Blueprint("Teoría de los microservicios", startDate, "LGAC1, LGCA2", "Asignada", "Tesina", "José Daniel Camarillo Villa", "Proyecto de investigación dedicado a la intervención...", "Jorge Octavio Ocharán Hernández", "Machine Learning");
        int updatedSuccessful = blueprint.modifyBlueprint(updatedBlueprint);
        Assert.assertEquals(1, updatedSuccessful);
    }
    
    @Test
    public void testConsultBlueprintByTitle() {
        BlueprintDAO blueprint = new BlueprintDAO();
        Blueprint blueprintResult;
        String blueprintTitleExpected = "Teoría de los microservicios";
        blueprintResult = blueprint.consultBlueprintByTitle(blueprintTitleExpected);
        String blueprintTitleActual = blueprintResult.getTitle();
        Assert.assertEquals("Prueba obtener otro Anteprojecto", blueprintTitleExpected, blueprintTitleActual);
    }
    
    @Test
    public void testConsultBlueprintByDate() {
        Date startDate = new Date((2024-1900), (10-1), 1);
        BlueprintDAO blueprint = new BlueprintDAO();
        Blueprint blueprintResult;
        String blueprintDateExpected = (new SimpleDateFormat("yyyy-MM-dd").format(startDate));
        blueprintResult = blueprint.consultBlueprintByDate(blueprintDateExpected);
        String blueprintDateActual = (new SimpleDateFormat("yyyy-MM-dd").format(blueprintResult.getStartDate()));
        Assert.assertEquals("Prueba obtener otro Anteprojecto", blueprintDateExpected, blueprintDateActual);
    }
   
    @Test
    public void testDeleteBlueprintByTitle() {
        BlueprintDAO blueprint = new BlueprintDAO();
        int deleteResult = blueprint.deleteBlueprintByTitle("Teoría de los microservicios");
        Assert.assertEquals(1, deleteResult);
    }
}
