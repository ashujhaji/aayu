package com.aayu.aayu.Model;

public class Cghs {
    public String cghs_name;
    public String cghs_address;
    public String cghs_contact;
    public Cghs(String cghs_name, String cghs_address, String cghs_contact){
        this.cghs_name=cghs_name;
        this.cghs_address=cghs_address;
        this.cghs_contact=cghs_contact;
    }

    public String getCghs_name() {
        return cghs_name;
    }

    public void setCghs_name(String cghs_name) {
        this.cghs_name = cghs_name;
    }

    public String getCghs_address() {
        return cghs_address;
    }

    public void setCghs_address(String cghs_address) {
        this.cghs_address = cghs_address;
    }

    public String getCghs_contact() {
        return cghs_contact;
    }

    public void setCghs_contact(String cghs_contact) {
        this.cghs_contact = cghs_contact;
    }
}
