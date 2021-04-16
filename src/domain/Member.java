package domain;

import java.util.Date;
/**
 *
 * @author Javier Blas
 */
public class Member {
    private String name; 
    private String levelOfStudy;
    private String studyArea;
    private String discipline;
    private boolean prodepParticipation;
    private String typeOfTeaching;
    private String lgac;
    private String ies;
    private int phoneNumber;
    private String institutionalMail;
    private Date dateOfBirth;
    private String curp;
    private String caCharge;

    public Member() {}
    
    public Member(String name, String levelOfStudy, String studyArea, String discipline, boolean prodepParticipation, String typeOfTeaching, String lgac, String ies, int phoneNumber, String institutionalMail, Date dateOfBirth, String curp, String caCharge) {
        this.name = name;
        this.levelOfStudy = levelOfStudy;
        this.studyArea = studyArea;
        this.discipline = discipline;
        this.prodepParticipation = prodepParticipation;
        this.typeOfTeaching = typeOfTeaching;
        this.lgac = lgac;
        this.ies = ies;
        this.phoneNumber = phoneNumber;
        this.institutionalMail = institutionalMail;
        this.dateOfBirth = dateOfBirth;
        this.curp = curp;
        this.caCharge = caCharge;
    }

    public String getName() {
        return name;
    }

    public String getLevelOfStudy() {
        return levelOfStudy;
    }

    public String getStudyArea() {
        return studyArea;
    }

    public String getDiscipline() {
        return discipline;
    }

    public boolean isProdepParticipation() {
        return prodepParticipation;
    }

    public String getTypeOfTeaching() {
        return typeOfTeaching;
    }

    public String getLgac() {
        return lgac;
    }

    public String getIes() {
        return ies;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getInstitutionalMail() {
        return institutionalMail;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCurp() {
        return curp;
    }

    public String getCaCharge() {
        return caCharge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevelOfStudy(String levelOfStudy) {
        this.levelOfStudy = levelOfStudy;
    }

    public void setStudyArea(String studyArea) {
        this.studyArea = studyArea;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setProdepParticipation(boolean prodepParticipation) {
        this.prodepParticipation = prodepParticipation;
    }

    public void setTypeOfTeaching(String typeOfTeaching) {
        this.typeOfTeaching = typeOfTeaching;
    }

    public void setLgac(String lgac) {
        this.lgac = lgac;
    }

    public void setIes(String ies) {
        this.ies = ies;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInstitutionalMail(String institutionalMail) {
        this.institutionalMail = institutionalMail;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setCaCharge(String caCharge) {
        this.caCharge = caCharge;
    }
    
}
