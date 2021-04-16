package businesslogic;

import domain.Project;
import java.util.ArrayList;

public interface IProjectDAO {
    public ArrayList<Project> consultListOfProjects();
    public int addProject(Project project);
    public int modifyProject(Project project);
    public Project consultProjectByTitle(String projectTitle); 
}