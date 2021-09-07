package com.ivladyka.requisitionappapiusers.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ivladyka.requisitionappapiusers.model.SmsCode;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SmsCodeServiceImpl implements SmsCodeService {

    //cache based on phone number and OTP MAX 8
    private static final Integer EXPIRE_MINS = 5;

    private final LoadingCache<String, Integer> otpCache;

    public SmsCodeServiceImpl() {
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    // User input one-time code validation
    @Override
    public boolean validateOTP(SmsCode smsCode) {
        SmsCodeDTO auth = (SmsCodeDTO) SecurityContextHolder.getContext().getAuthentication();
        int cachedOTP = this.getOTP(auth.getPhoneNumber());
        if (String.valueOf(cachedOTP).equals(smsCode.getCode())) {
            this.clearOTP(auth.getPhoneNumber());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>
                    (Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
            SmsCodeDTO smsCodeDTO = new SmsCodeDTO(auth.getPhoneNumber(), auth.getCode(), grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(smsCodeDTO);
            return true;
        } else {
            return false;
        }
    }

    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public int generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    //This method is used to return the OPT number against Key->Key values is username
    public int getOTP(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }
    }


    //This method is used to clear the OTP catched already
    public void clearOTP(String key) {
        otpCache.invalidate(key);
    }
}
