package com.abewkayew.serafelagi;

/**
 *
 */
/*
This class is to take data from users who want to apply for the job and post it
to the server. it used in ApplyNow_RecylcerAdapter,in applyNowForm
 */

public class Apply_Now_data {
    private String name_of_university;
    private String gpa;
    private String tempoFile;
    private int Sync_status;

    public Apply_Now_data(String name_of_university, String gpa, String tempoFile, int sync_status) {
        this.name_of_university = name_of_university;
        this.gpa = gpa;
        this.tempoFile = tempoFile;
        Sync_status = sync_status;
    }

    public String getName_of_university() {
        return name_of_university;

    }

    public void setName_of_university(String name_of_university) {
        this.name_of_university = name_of_university;

    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getTempoFile() {
        return tempoFile;
    }

    public void setTempoFile(String tempoFile) {
        this.tempoFile = tempoFile;
    }

    public int getSync_status() {
        return Sync_status;
    }

    public void setSync_status(int sync_status) {
        Sync_status = sync_status;
    }
}
