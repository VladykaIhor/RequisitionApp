package com.ivladyka.requisitionappapiusers.model.requisition;

import com.ivladyka.requisitionappapiusers.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameInfo extends BaseModel {

    @OneToOne(mappedBy = "id")
    private Requisition id;

    private String name;

    private String lastName;

    private String fathersName;
}
