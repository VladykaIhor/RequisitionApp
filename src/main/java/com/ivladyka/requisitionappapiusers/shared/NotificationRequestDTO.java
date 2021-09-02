package com.ivladyka.requisitionappapiusers.shared;

public class NotificationRequestDTO {
    private String email;
    private String phonenumber;

    public NotificationRequestDTO(String email, String phonenumber) {
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
