package domain;

import java.util.Date;
/**
 *
 * @author Javier Blas
 */
public class Member {
    private String fullName;
    private Date dateOfBirth;
    private String curp;
    private String phoneNumber;
    private String institutionalMail;
    private String discipline;
    private String studyGrade;
    private String studyArea;
    private String typeOfTeaching;
    private String lgac;
    private String ies;
    private String prodepParticipation;
    private String position;
    private String keycodeAcademicGroup;

    public Member(String fullName, Date dateOfBirth, String curp, String phoneNumber, String institutionalMail, String discipline, String studyGrade, String studyArea, String typeOfTeaching, String lgac, String ies, String prodepParticipation, String position, String keycodeAcademicGroup) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.curp = curp;
        this.phoneNumber = phoneNumber;
        this.institutionalMail = institutionalMail;
        this.discipline = discipline;
        this.studyGrade = studyGrade;
        this.studyArea = studyArea;
        this.typeOfTeaching = typeOfTeaching;
        this.lgac = lgac;
        this.ies = ies;
        this.prodepParticipation = prodepParticipation;
        this.position = position;
        this.keycodeAcademicGroup = keycodeAcademicGroup;
    }

    public Member() {
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCurp() {
        return curp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getInstitutionalMail() {
        return institutionalMail;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getStudyGrade() {
        return studyGrade;
    }

    public String getStudyArea() {
        return studyArea;
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

    public String isProdepParticipation() {
        return prodepParticipation;
    }

    public String getPosition() {
        return position;
    }

    public String getKeycodeAcademicGroup() {
        return keycodeAcademicGroup;
    }

    public String getProdepParticipation() {
        return prodepParticipation;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInstitutionalMail(String institutionalMail) {
        this.institutionalMail = institutionalMail;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setStudyGrade(String studyGrade) {
        this.studyGrade = studyGrade;
    }

    public void setStudyArea(String studyArea) {
        this.studyArea = studyArea;
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

    public void setProdepParticipation(String prodepParticipation) {
        this.prodepParticipation = prodepParticipation;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setKeycodeAcademicGroup(String keycodeAcademicGroup) {
        this.keycodeAcademicGroup = keycodeAcademicGroup;
    }
}
