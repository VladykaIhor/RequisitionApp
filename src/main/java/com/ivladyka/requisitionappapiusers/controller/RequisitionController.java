package com.ivladyka.requisitionappapiusers.controller;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.model.requisition.NameInfo;
import com.ivladyka.requisitionappapiusers.model.requisition.Requisition;
import com.ivladyka.requisitionappapiusers.service.NameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requisition")
public class RequisitionController {

    private final NameInfoService nameInfoService;

    @Autowired
    public RequisitionController(NameInfoService nameInfoService) {
        this.nameInfoService = nameInfoService;
    }

    @PostMapping("/name-info")
    private String fillNameInfo(@RequestBody NameInfo nameInfo) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Requisition requisition = new Requisition();
//        requisition.setUser(currentUser);
        NameInfo info = nameInfoService.saveNameInfo(nameInfo);
//        requisition.setNameInfo(info);
        return null;
    }

    @PostMapping("/social-info")
    private String fillSocialInfo() {
        return null;
    }

    @PostMapping("/income-email-info/")
    private String fillIncomeAndEmail() {
        return null;
    }


}
