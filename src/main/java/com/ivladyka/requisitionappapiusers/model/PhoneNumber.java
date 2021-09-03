package com.ivladyka.requisitionappapiusers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    @Id
    private Long id;
    @NotEmpty
    private String value;

    @NotEmpty
    private String locale;
}
