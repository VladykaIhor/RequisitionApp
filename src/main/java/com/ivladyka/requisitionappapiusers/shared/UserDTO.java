package com.ivladyka.requisitionappapiusers.shared;

import com.ivladyka.requisitionappapiusers.model.SmsCode;

public class UserDTO extends BaseDTO {
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
