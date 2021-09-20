package com.ivladyka.requisitionappapiusers.shared;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.time.LocalDateTime;
import java.util.Collection;

public class SmsCodeDTO extends AbstractAuthenticationToken {
    private String otp;
    private String principal;
    private LocalDateTime expireTime;

    public SmsCodeDTO() {
        super(null);
    }

    public SmsCodeDTO(String phoneNumber, String otp) {
        super(null);
        this.principal = phoneNumber;
        this.otp = otp;
        setAuthenticated(false);
    }

    public SmsCodeDTO(String phoneNumber, String otp, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = phoneNumber;
        this.otp = otp;
    }

    @Override
    public boolean isAuthenticated() {
        return super.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return otp;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
