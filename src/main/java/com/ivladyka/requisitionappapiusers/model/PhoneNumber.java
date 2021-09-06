package com.ivladyka.requisitionappapiusers.model;

import com.ivladyka.requisitionappapiusers.util.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.Annotation;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber implements Phone {
    @Id
    private Long id;
    @NotEmpty
    private String value;

    @NotEmpty
    private String locale;

    @Override
    public String locale() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public Class<?>[] groups() {
        return new Class[0];
    }

    @Override
    public Class<? extends Payload>[] payload() {
        return new Class[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
