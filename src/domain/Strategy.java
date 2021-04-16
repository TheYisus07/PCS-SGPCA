package domain;

/**
 * 
 * @author daniCV
 */

public class Strategy {
    private int number;
    private String goal;
    private String description;
    private String action;
    private String result;
    private int objectiveID;

    public Strategy(int number, String goal, String description, String action, String result, int objectiveID) {
        this.number = number;
        this.goal = goal;
        this.description = description;
        this.action = action;
        this.result = result;
        this.objectiveID = objectiveID;
    }
    
    public int getNumber() {
        return number;
    }

    public String getGoal() {
        return goal;
    }

    public String getDescription() {
        return description;
    }

    public String getAction() {
        return action;
    }

    public String getResult() {
        return result;
    }

    public int getObjectiveID() {
        return objectiveID;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setObjectiveID(int objectiveID) {
        this.objectiveID = objectiveID;
    }
}
