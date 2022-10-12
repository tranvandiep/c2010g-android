package com.gokisoft.c2010g.lesson02;

public class Student {
    String fullname;
    String email;
    String address;

    public Student() {
        fullname = "";
        email = "";
        address = "";
    }

    public Student(String fullname, String email, String address) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
