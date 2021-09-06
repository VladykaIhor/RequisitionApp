package com.ivladyka.requisitionappapiusers.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NameInfoDTO implements Serializable {
    private String name;

    private String lastName;

    private String fathersName;
}
