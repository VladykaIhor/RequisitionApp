package com.ivladyka.requisitionappapiusers.model.requisition;

import com.ivladyka.requisitionappapiusers.model.BaseModel;
import com.ivladyka.requisitionappapiusers.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "requisition")
@NoArgsConstructor
@AllArgsConstructor
public class Requisition extends BaseModel {

    @ManyToOne
    private User user;

    private String name;
}
