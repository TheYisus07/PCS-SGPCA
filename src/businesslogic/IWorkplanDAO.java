package businesslogic;

import domain.Workplan;
import domain.Objective;
import domain.Strategy;
import java.util.ArrayList;

public interface IWorkplanDAO {
    public int addWorkPlan(Workplan workplan);
    public Workplan consultWorkplanByKeyCode(String keyCode);
    public int manageWorkPlan(Workplan workplan);
    public int addObjetive(Objective objective);
    public int addStrategy(Strategy strategy);
    public int modifyObjective(Objective objective, int objectiveId);
    public ArrayList<Workplan> consultListOfWorkplans();
    public int deleteStrategyByObjectiveId(int numberStrategy, int idObjective);
    public ArrayList<Objective> consultListOfObjectives();
    public ArrayList<Strategy> consulListOfStrategys();
}