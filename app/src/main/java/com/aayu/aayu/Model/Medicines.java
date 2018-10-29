package com.aayu.aayu.Model;

public class Medicines {
    public String med_name;
    private String availability;

    public Medicines(String med_name, String availability){
        this.med_name=med_name;
        this.availability=availability;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
