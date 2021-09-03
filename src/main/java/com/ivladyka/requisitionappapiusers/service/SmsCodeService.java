package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.SmsCode;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;

public interface SmsCodeService {

    void clearOTP(String key);

    int generateOTP(String key);

    int getOTP(String key);
}
