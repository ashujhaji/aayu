package com.aayu.aayu.Model;

import java.io.Serializable;

public class Prescriptions implements Serializable {
    private static final long serialVersionUID = -3948966179855286411L;
    String name, url, status;
    public Prescriptions(String name, String url, String status){
        this.name = name;
        this.url = url;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
