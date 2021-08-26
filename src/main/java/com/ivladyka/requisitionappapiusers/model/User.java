package com.ivladyka.requisitionappapiusers.model;


import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

//User request model
@Entity
@Table(name = "users")
public class User extends BaseModel implements UserDetails {

    @Size(min = 10, max = 13)
    @NotNull(message = "Phone number cannot be empty")
    private String phoneNumber;
    private int oneTimePassword;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private SmsCode oneTimePassword;

    public int getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(int oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//    public SmsCode getOneTimePassword() {
//        return oneTimePassword;
//    }

//    public void setOneTimePassword(SmsCode oneTimePassword) {
//        this.oneTimePassword = oneTimePassword;
//    }
}
