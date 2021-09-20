package com.ivladyka.requisitionappapiusers.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ivladyka.requisitionappapiusers.model.SmsCode;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public SmsCodeDTO sendOtp(SmsCodeDTO smsCodeDTO) {
        String phoneNumber = String.valueOf(smsCodeDTO.getPrincipal());
        String otp = this.generateOTP(phoneNumber);
        //logic to send OTP via SMS use SMSNotificationService, currently console output, use
        System.out.println("Your one-time password is " + otp);
        smsCodeDTO.setExpireTime(LocalDateTime.now().plusSeconds(120));
        return smsCodeDTO;
    }

    // User input one-time code validation
    @Override
    public boolean validateOTP(SmsCode smsCode) {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        SmsCodeDTO auth = (SmsCodeDTO) principal;
//        if (principal instanceof SmsCodeDTO) {
//            String phoneNumberKey = String.valueOf(auth.getPrincipal());
////            int cachedOTP = this.getOTP(phoneNumberKey);
//            if (String.valueOf(cachedOTP).equals(smsCode.getCode())) {
//                this.clearOTP(String.valueOf(auth.getPrincipal()));
//                List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>
//                        (Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//                SmsCodeDTO smsCodeDTO = new SmsCodeDTO(phoneNumberKey, auth.getOtp(), grantedAuthorities);
//                SecurityContextHolder.getContext().setAuthentication(smsCodeDTO);
//                return true;
//            } else {
//                return false;
//            }
//        } else return false;
        return false;
    }

    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public String generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return String.valueOf(otp);
    }

    //This method is used to return the OPT number against Key->Key values is username
    public String getOTP(String key) {
        try {
            return String.valueOf(otpCache.get(key));
        } catch (Exception e) {
            return "";
        }
    }


    //This method is used to clear the OTP catched already
    public void clearOTP(String key) {
        otpCache.invalidate(key);
    }
}
