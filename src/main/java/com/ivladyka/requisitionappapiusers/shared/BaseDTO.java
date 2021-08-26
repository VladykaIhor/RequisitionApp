package com.ivladyka.requisitionappapiusers.shared;

import java.io.Serializable;

public class BaseDTO implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
