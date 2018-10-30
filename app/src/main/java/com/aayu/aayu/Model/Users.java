package com.aayu.aayu.Model;

public class Users {
    public String uname;
    public String dob;
    public  String mobile;

    public Users(String uname, String dob, String mobile){
        this.uname=uname;
        this.dob=dob;
        this.mobile=mobile;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
