package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.SmsCode;

public interface SmsCodeService {

    void clearOTP(String key);

    int generateOTP(String key);

    int getOTP(String key);

    boolean validateOTP(SmsCode smsCode);
}
