package com.example.tempolend;

import android.widget.EditText;

public class HelperClass {

    String username;
    String email;
    String password;
    private String confirm;

    public HelperClass(String username) {
        this.username = username;
    }

    public HelperClass(String user_name, String email, String signup_pass, String confirm_pass, String address, String contact_no) {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getconfirm() {
        return confirm;
    }

    public void setconfirm(String password) {
        this.confirm = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    String address;

    public HelperClass(String fullname, String email, String username, String password, String address, String contactno) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.address = address;
        this.contactno = contactno;
    }

    String contactno;

    public HelperClass() {
    }
}
