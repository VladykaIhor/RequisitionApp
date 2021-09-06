package com.ivladyka.requisitionappapiusers.shared;

import com.ivladyka.requisitionappapiusers.model.User;

import javax.persistence.ManyToOne;

public class RequisitionDTO {

    @ManyToOne
    private User user;

    private String name;
}
