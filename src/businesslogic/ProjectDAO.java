package businesslogic;

import dataaccess.Conexion;
import domain.Project;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author daniCV
 */

public class ProjectDAO implements IProjectDAO {
    
    private final Conexion connection = new Conexion();

    @Override
    public ArrayList<Project> consultListOfProjects() {
        ArrayList<Project> arrayListProjects = new ArrayList<>();
        String query = "SELECT Title, EstimatedFinishDate, StartDate, AssociatesIGAC, Description, Participants, member_FullName FROM project";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListProjects.add(new Project(resultSet.getString("Title"), resultSet.getDate("EstimatedFinishDate"), resultSet.getDate("StartDate"), resultSet.getString("AssociatesIGAC"), resultSet.getString("Description"), resultSet.getString("Participants"), resultSet.getString("member_FullName"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListProjects;
    }
    
    @Override
    public int addProject(Project project) {
        String estimatedFinishDate = (new SimpleDateFormat("yyyy-MM-dd").format(project.getEstimedFinishDate()));
        String startDate = (new SimpleDateFormat("yyyy-MM-dd").format(project.getStartDate()));
        String query = "INSERT INTO project (Title, EstimatedFinishDate, StartDate, AssociatesIGAC, Description, Participants, member_FullName) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setString(2, estimatedFinishDate);              
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, project.getAssociatesIGAC());
            preparedStatement.setString(5, project.getDescripcion());
            preparedStatement.setString(6, project.getParticipants());
            preparedStatement.setString(7, project.getMemberFullName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }
    
    @Override
    public int modifyProject(Project project) {
        String estimatedFinishDate = (new SimpleDateFormat("yyyy-MM-dd").format(project.getEstimedFinishDate()));
        String startDate = (new SimpleDateFormat("yyyy-MM-dd").format(project.getStartDate()));
        String query = "UPDATE project SET Title = ?, EstimatedFinishDate = ?, StartDate = ?, AssociatesIGAC = ?, Description = ?, Participants = ? WHERE Title = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setString(2, estimatedFinishDate);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, project.getAssociatesIGAC());
            preparedStatement.setString(5, project.getDescripcion());
            preparedStatement.setString(6, project.getParticipants());
            preparedStatement.setString(7, project.getTitle());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }
    
    @Override
    public Project consultProjectByTitle(String projectTitle) {
        String query = "SELECT Title, EstimatedFinishDate, StartDate, AssociatesIGAC, Description, Participants, member_FullName FROM project WHERE Title = ?";
        Project project = new Project();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, projectTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project.setTitle(resultSet.getString("Title"));
                project.setEstimedFinishDate(resultSet.getDate("EstimatedFinishDate"));
                project.setStartDate(resultSet.getDate("StartDate"));
                project.setAssociatesIGAC(resultSet.getString("AssociatesIGAC"));
                project.setDescripcion(resultSet.getString("Description"));
                project.setParticipants(resultSet.getString("Participants"));
                project.setMemberFullName(resultSet.getString("member_FullName"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return project; 
    }
}