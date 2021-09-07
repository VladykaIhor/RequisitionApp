package com.ivladyka.requisitionappapiusers.model.requisition;


import com.ivladyka.requisitionappapiusers.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "income_and_email_info")
@NoArgsConstructor
@AllArgsConstructor
public class IncomeAndEmailInfo extends BaseModel {

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Requisition.class)
    private Long requisitionId;

    private Long yearlyIncome;

    private String email;
}
