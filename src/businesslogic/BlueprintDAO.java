package businesslogic;

import dataaccess.Conexion;
import domain.Blueprint;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author daniCV
 */

public class BlueprintDAO implements IBlueprintDAO {
    private final Conexion connection = new Conexion();

    @Override
    public int addBlueprint(Blueprint blueprint) {
        String query = "INSERT INTO blueprint (Title, StartDate, AssociatedLGAC, Satatus, Modality, Student, Description, Codirectors, project_Title) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String startDate = (new SimpleDateFormat("yyyy-MM-dd").format(blueprint.getStartDate()));
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, blueprint.getTitle());
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, blueprint.getAssociatedLGAC());
            preparedStatement.setString(4, blueprint.getStatus());
            preparedStatement.setString(5, blueprint.getModality());
            preparedStatement.setString(6, blueprint.getStudent());
            preparedStatement.setString(7, blueprint.getDescription());
            preparedStatement.setString(8, blueprint.getCodirectors());
            preparedStatement.setString(9, blueprint.getProjectTitle());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }

    @Override
    public int modifyBlueprint(Blueprint blueprint) {
        String query = "UPDATE blueprint SET Title = ?, StartDate = ?, AssociatedLGAC = ?, Satatus = ?, Modality = ?, Student = ?, Description  = ?, Codirectors = ?, project_Title = ? WHERE Title = ?";
        String startDate = (new SimpleDateFormat("yyyy-MM-dd").format(blueprint.getStartDate()));
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, blueprint.getTitle());
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, blueprint.getAssociatedLGAC());
            preparedStatement.setString(4, blueprint.getStatus());
            preparedStatement.setString(5, blueprint.getModality());
            preparedStatement.setString(6, blueprint.getStudent());
            preparedStatement.setString(7, blueprint.getDescription());
            preparedStatement.setString(8, blueprint.getCodirectors());
            preparedStatement.setString(9, blueprint.getProjectTitle());
            preparedStatement.setString(10, blueprint.getTitle());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }

    @Override
    public Blueprint consultBlueprintByTitle(String blueprintTitle) {
        String query = "SELECT Title, StartDate, AssociatedLGAC, Satatus, Modality, Student, Description, Codirectors, project_Title FROM blueprint WHERE Title = ?";
        Blueprint blueprint = new Blueprint();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, blueprintTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blueprint.setTitle(resultSet.getString("Title"));
                blueprint.setStartDate(resultSet.getDate("StartDate"));
                blueprint.setAssociatedLGAC(resultSet.getString("AssociatedLGAC"));
                blueprint.setStatus(resultSet.getString("Satatus"));
                blueprint.setModality(resultSet.getString("Modality"));
                blueprint.setStudent(resultSet.getString("Student"));
                blueprint.setDescription(resultSet.getString("Description"));
                blueprint.setCodirectors(resultSet.getString("Codirectors"));
                blueprint.setProjectTitle(resultSet.getString("project_Title"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return blueprint;
    }

    @Override
    public Blueprint consultBlueprintByDate(String blueprintDate) {
        String query = "SELECT Title, StartDate, AssociatedLGAC, Satatus, Modality, Student, Description, Codirectors, project_Title FROM blueprint WHERE StartDate = ?";
        Blueprint blueprint = new Blueprint();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, blueprintDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blueprint.setTitle(resultSet.getString("Title"));
                blueprint.setStartDate(resultSet.getDate("StartDate"));
                blueprint.setAssociatedLGAC(resultSet.getString("AssociatedLGAC"));
                blueprint.setStatus(resultSet.getString("Satatus"));
                blueprint.setModality(resultSet.getString("Modality"));
                blueprint.setStudent(resultSet.getString("Student"));
                blueprint.setDescription(resultSet.getString("Description"));
                blueprint.setCodirectors(resultSet.getString("Codirectors"));
                blueprint.setProjectTitle(resultSet.getString("project_Title"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return blueprint;
    }

    @Override
    public int deleteBlueprintByTitle(String blueprintTitle) {
        String query = "DELETE FROM blueprint WHERE Title = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, blueprintTitle);
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
