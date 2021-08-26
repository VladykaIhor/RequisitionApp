package com.ivladyka.requisitionappapiusers.shared;

import com.ivladyka.requisitionappapiusers.model.SmsCode;

public class UserDTO extends BaseDTO {
    private String phoneNumber;
    private SmsCode oneTimePassword;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SmsCode getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(SmsCode oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }
}
