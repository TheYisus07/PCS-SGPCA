package domain;

import java.util.Date;

/**
 * 
 * @author daniCV
 */

public class Evidence {
    private Date registrationDate;
    private String status;
    private String impactOnCA;

    public Evidence(int idEvidence, Date registrationDate, String status, String impactOnCA) {
        this.registrationDate = registrationDate;
        this.status = status;
        this.impactOnCA = impactOnCA;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getImpactOnCA() {
        return impactOnCA;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImpactOnCA(String impactOnCA) {
        this.impactOnCA = impactOnCA;
    }
}
