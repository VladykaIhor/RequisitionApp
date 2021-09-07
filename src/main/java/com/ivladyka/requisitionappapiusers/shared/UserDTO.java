package com.ivladyka.requisitionappapiusers.shared;

import com.ivladyka.requisitionappapiusers.model.requisition.Requisition;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private String phoneNumber;
    private List<Requisition> requisitionList;
}
