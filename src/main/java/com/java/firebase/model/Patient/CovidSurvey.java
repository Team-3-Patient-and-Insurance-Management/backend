package com.java.firebase.model.Patient;

public class CovidSurvey {
    private String experiencedSymptoms;
    private String closePhysicalContact;

    private String positiveCovid90Days;
    private String selfMonitor;
    private String wantCovidTest;
    public String getExperiencedSymptoms() {
        return experiencedSymptoms;
    }

    public void setExperiencedSymptoms(String experiencedSymptoms) {
        this.experiencedSymptoms = experiencedSymptoms;
    }

    public String getClosePhysicalContact() {
        return closePhysicalContact;
    }

    public void setClosePhysicalContact(String closePhysicalContact) {
        this.closePhysicalContact = closePhysicalContact;
    }

    public String getPositiveCovid90Days() {
        return positiveCovid90Days;
    }

    public void setPositiveCovid90Days(String positiveCovid90Days) {
        this.positiveCovid90Days = positiveCovid90Days;
    }

    public String getSelfMonitor() {
        return selfMonitor;
    }

    public void setSelfMonitor(String selfMonitor) {
        this.selfMonitor = selfMonitor;
    }

    public String getWantCovidTest() {
        return wantCovidTest;
    }

    public void setWantCovidTest(String wantCovidTest) {
        this.wantCovidTest = wantCovidTest;
    }

}
