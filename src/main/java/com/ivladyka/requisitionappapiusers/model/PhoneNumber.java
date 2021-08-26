package com.ivladyka.requisitionappapiusers.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class PhoneNumber {
    @Id
    private Long id;
    @NotEmpty
    private String value;

    @NotEmpty
    private String locale;

    public PhoneNumber() {
    }

    public PhoneNumber(String value, String locale) {
        this.value = value;
        this.locale = locale;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
