package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.SmsCode;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;

public interface SmsCodeService {

    void clearOTP(String key);

    String generateOTP(String key);

    String getOTP(String key);

    SmsCodeDTO sendOtp(SmsCodeDTO smsCodeDTO);

    boolean validateOTP(SmsCode smsCode);
}
