package com.example.eltur.parkinsonbp.ServerClass;

import com.example.eltur.parkinsonbp.Activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static com.example.eltur.parkinsonbp.Utils.UtilsMethod.CustomDateFormat;


public class PatientRecord {

       // private Long patientRecordID;
    //key + login ID
        private String patientID;
        private Date patientLastUpdate;//format yyyy-mm-dd
        private Collection<Activity> listOfActivitiy = new ArrayList();
        private Collection<Medicine> listOfMedicine = new ArrayList();
        @Override
        public String toString(){

            return String.format("{patientID:%s,patientLastUpdate:%s,listOfActivitiy:%s,listOfMedicine:%s}", patientID,CustomDateFormat().format(patientLastUpdate),listOfActivitiy,listOfMedicine);
        }
        public PatientRecord(){
            patientLastUpdate = new Date();
        }

    public PatientRecord(String patientID, Date patientLastUpdate, Collection<Activity> listOfActivitiy, Collection<Medicine> listOfMedicine) {
        this.patientID = patientID;
        this.patientLastUpdate = patientLastUpdate;
        this.listOfActivitiy = listOfActivitiy;
        this.listOfMedicine = listOfMedicine;
    }

    public PatientRecord(String patientID, Date patientLastUpdate, Collection<Activity> listOfActivitiy) {
        this.patientID = patientID;
        this.patientLastUpdate = patientLastUpdate;
        this.listOfActivitiy = listOfActivitiy;;
    }
//    public Long getPatientRecordID() {
//        return patientRecordID;
//    }
//
//    public void setPatientRecordID(Long patientRecordID) {
//        this.patientRecordID = patientRecordID;
//    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Date getPatientLastUpdate() {
        return patientLastUpdate;
    }

    public void setPatientLastUpdate(Date patientLastUpdate) {
        this.patientLastUpdate = patientLastUpdate;
    }

    public Collection<Activity> getListOfActivitiy() {
        return listOfActivitiy;
    }

    public void setListOfActivitiy(Collection<Activity> listOfActivitiy) {
        this.listOfActivitiy = listOfActivitiy;
    }

    public Collection<Medicine> getListOfMedicine() {
        return listOfMedicine;
    }

    public void setListOfMedicine(Collection<Medicine> listOfMedicine) {
        this.listOfMedicine = listOfMedicine;
    }
}
