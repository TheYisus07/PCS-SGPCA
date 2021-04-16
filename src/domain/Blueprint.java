package domain;

import java.util.Date;

/**
 * 
 * @author daniCV
 */

public class Blueprint {
    private String title;
    private Date startDate;
    private String associatedLGAC;
    private String status;
    private String student;
    private String codirectors;
    private String projectTitle; 

    public Blueprint(String title, Date startDate, String associatedLGAC, String status, String student, String codirectors, String projectString) {
        this.title = title;
        this.startDate = startDate;
        this.associatedLGAC = associatedLGAC;
        this.status = status;
        this.student = student;
        this.codirectors = codirectors;
        this.projectTitle = projectTitle;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getAssociatedLGAC() {
        return associatedLGAC;
    }

    public String getStatus() {
        return status;
    }

    public String getStudent() {
        return student;
    }

    public String getCodirectors() {
        return codirectors;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setAssociatedLGAC(String associatedLGAC) {
        this.associatedLGAC = associatedLGAC;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setCodirectors(String codirectors) {
        this.codirectors = codirectors;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
