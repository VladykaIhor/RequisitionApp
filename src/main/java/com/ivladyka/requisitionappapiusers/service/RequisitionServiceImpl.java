package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.requisition.Requisition;
import com.ivladyka.requisitionappapiusers.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequisitionServiceImpl implements RequisitionService {

    private final RequisitionRepository requisitionRepository;

    @Autowired
    public RequisitionServiceImpl(RequisitionRepository requisitionRepository) {
        this.requisitionRepository = requisitionRepository;
    }

    @Override
    public Requisition saveRequisition(Requisition requisition) {
        return requisitionRepository.save(requisition);
    }

    @Override
    public Requisition updateNameInfo(Requisition requisition, Long nameInfoId) {
        Optional<Requisition> byId = requisitionRepository.findById(requisition.getId());
        return null;
    }

    @Override
    public Requisition updateSocialInfo(Requisition requisition, Long socialInfoId) {
        return null;
    }

    @Override
    public Requisition updateIncomeAndEmailInfo(Requisition requisition, Long incomeAndEmailId) {
        return null;
    }


}
