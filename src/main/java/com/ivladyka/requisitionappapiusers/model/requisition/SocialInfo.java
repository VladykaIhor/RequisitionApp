package com.ivladyka.requisitionappapiusers.model.requisition;

import com.ivladyka.requisitionappapiusers.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "social_info")
@NoArgsConstructor
@AllArgsConstructor
public class SocialInfo extends BaseModel {

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Requisition.class)
    private Long requisitionId;

    private String passportNumber;

    private boolean marriageStatus;

}
