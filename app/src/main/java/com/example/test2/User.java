package com.example.test2;

public class User {
    String firstname , lastname,email;
    String w;
    public User(){}

    public User(String firstname, String lastname, String w,String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.w = w;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getT() {
        return w;
    }

    public void setT(String t) {
        this.w= t;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", w=" + w +
                '}';
    }
}
