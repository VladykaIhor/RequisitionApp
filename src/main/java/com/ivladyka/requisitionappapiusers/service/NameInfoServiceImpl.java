package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.requisition.NameInfo;
import com.ivladyka.requisitionappapiusers.repository.NameInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NameInfoServiceImpl implements NameInfoService {

    private final NameInfoRepository nameInfoRepository;

    @Autowired
    public NameInfoServiceImpl(NameInfoRepository nameInfoRepository) {
        this.nameInfoRepository = nameInfoRepository;
    }

    @Override
    public NameInfo saveNameInfo(NameInfo nameInfo) {
        return nameInfoRepository.save(nameInfo);
    }
}
