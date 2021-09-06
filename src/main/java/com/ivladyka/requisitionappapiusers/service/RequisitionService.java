package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.requisition.Requisition;

public interface RequisitionService {

    Requisition saveRequisition(Requisition requisition);

    Requisition updateNameInfo(Requisition requisition, Long nameInfoId);

    Requisition updateSocialInfo(Requisition requisition, Long socialInfoId);

    Requisition updateIncomeAndEmailInfo(Requisition requisition, Long incomeAndEmailId);
}

