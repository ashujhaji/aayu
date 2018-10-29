package com.aayu.aayu.Model;

public class Doctors {
    public String doc_name;
    public String doc_id;
    public String timing;
    public String specialisation;
    public String days;
    public String place_id;
    public Doctors(String doc_name, String doc_id, String timing, String specialisation, String days, String place_id){
        this.doc_name=doc_name;
        this.doc_id=doc_id;
        this.timing=timing;
        this.specialisation=specialisation;
        this.days=days;
        this.place_id=place_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }
}
