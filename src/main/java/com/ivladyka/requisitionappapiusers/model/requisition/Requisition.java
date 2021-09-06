package com.ivladyka.requisitionappapiusers.model.requisition;

import com.ivladyka.requisitionappapiusers.model.BaseModel;
import com.ivladyka.requisitionappapiusers.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Requisition extends BaseModel {

    @ManyToOne
    private User user;

    private String name;

    @OneToOne
    private NameInfo nameInfo;

    @OneToOne
    private SocialInfo socialInfo;

    @OneToOne
    private IncomeAndEmailInfo incomeAndEmailInfo;
}
