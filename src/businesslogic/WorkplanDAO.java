package businesslogic;

import dataaccess.Conexion;
import domain.Objective;
import domain.Strategy;
import domain.Workplan;
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

public class WorkplanDAO implements IWorkplanDAO {
    private final Conexion connection = new Conexion();
    
    @Override
    public int addWorkPlan(Workplan workplan) {
        String query = "INSERT INTO workplan (Keycode, StartDate, FinishDate, member_FullName) VALUES (?, ?, ?, ?)";
        String startDate = (new SimpleDateFormat("yyyy-MM-dd").format(workplan.getStartDate()));
        String finishDate = (new SimpleDateFormat("yyyy-MM-dd").format(workplan.getFinishDate()));
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, workplan.getKeyCode());
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, finishDate);
            preparedStatement.setString(4, workplan.getMemberFullName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }
    
    @Override
    public Workplan consultWorkplanByKeyCode(String workplanKeyCode){
        String query = "SELECT Keycode, StartDate, FinishDate, member_FullName FROM workplan WHERE Keycode = ?";
        Workplan workplan = new Workplan(); 
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, workplanKeyCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                workplan.setKeyCode(resultSet.getString("Keycode"));                    
                workplan.setStartDate(resultSet.getDate("StartDate"));
                workplan.setFinishDate(resultSet.getDate("FinishDate")); 
                workplan.setMemberFullName(resultSet.getString("member_FullName"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return workplan;
    }

    @Override
    public int manageWorkPlan(Workplan workplan) {
        String query = "UPDATE workplan SET Keycode = ?, StartDate = ?, FinishDate = ?, member_FullName = ? WHERE Keycode = ?";
        String startDate = (new SimpleDateFormat("yyyy-MM-dd").format(workplan.getStartDate()));
        String finishDate = (new SimpleDateFormat("yyyy-MM-dd").format(workplan.getFinishDate()));
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, workplan.getKeyCode());
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, finishDate);
            preparedStatement.setString(4, workplan.getMemberFullName());
            preparedStatement.setString(5, workplan.getKeyCode());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }

    @Override
    public int addObjetive(Objective objective) {    
        String query = "INSERT INTO objective (Title, Description, Status, workplan_Keycode) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, objective.getTitle());
            preparedStatement.setString(2, objective.getDescription());
            preparedStatement.setString(3, objective.getStatus());
            preparedStatement.setString(4, objective.getWorkplanKeyCode());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }
    
    @Override
    public int addStrategy(Strategy strategy) {
        String query = "INSERT INTO strategy (Number, Goal, Description, Action, Result, objective_IDobjective) VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, strategy.getNumber());
            preparedStatement.setString(2, strategy.getGoal());
            preparedStatement.setString(3, strategy.getDescription());
            preparedStatement.setString(4, strategy.getAction());
            preparedStatement.setString(5, strategy.getResult());
            preparedStatement.setInt(6, strategy.getObjectiveID());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }

    @Override
    public int modifyObjective(Objective objective, int objectiveId) {
        String query = "UPDATE objective SET Title = ?, Description = ?, Status = ? WHERE IDobjective = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, objective.getTitle());
            preparedStatement.setString(2, objective.getDescription());
            preparedStatement.setString(3, objective.getStatus());
            preparedStatement.setInt(4, objectiveId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return result;
    }

    @Override
    public ArrayList<Workplan> consultListOfWorkplans() {
        String query = "SELECT Keycode, StartDate, FinishDate, member_FullName FROM workplan";
        ArrayList<Workplan> arrayListWorkplans = new ArrayList<>();     
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListWorkplans.add(new Workplan(resultSet.getString("Keycode"), resultSet.getDate("StartDate"), resultSet.getDate("FinishDate"), resultSet.getString("member_FullName"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListWorkplans;
    }
    
    @Override
    public int deleteStrategyByObjectiveId(int numberStrategy, int idObjective) {
        String query = "DELETE FROM strategy WHERE Number = ? AND objective_IDobjective = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, numberStrategy);
            preparedStatement.setInt(2, idObjective);
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public ArrayList<Objective> consultListOfObjectives() {
        String query = "SELECT IDobjective, Title, Description, Status, workplan_Keycode FROM objective";
        ArrayList<Objective> arrayListObjectives = new ArrayList<>();     
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListObjectives.add(new Objective(resultSet.getInt("IDobjective"), resultSet.getString("Title"), resultSet.getString("Description"), resultSet.getString("Status"), resultSet.getString("workplan_Keycode"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListObjectives;
    }
    
    @Override
    public ArrayList<Strategy> consulListOfStrategys() {
        String query = "SELECT IDStrategy, Number, Goal, Description, Action, Result, objective_IDobjective FROM strategy";
        ArrayList<Strategy> arrayListStrategys = new ArrayList<>();     
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                arrayListStrategys.add(new Strategy(resultSet.getInt("Number"), resultSet.getString("Goal"), resultSet.getString("Description"), resultSet.getString("Action"), resultSet.getString("Result"), resultSet.getInt("objective_IDobjective"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListStrategys;
    }
}