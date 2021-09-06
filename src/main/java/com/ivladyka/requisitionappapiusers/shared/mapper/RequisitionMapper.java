package com.ivladyka.requisitionappapiusers.shared.mapper;

import com.ivladyka.requisitionappapiusers.model.requisition.Requisition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequisitionMapper {

    RequisitionMapper INSTANCE = Mappers.getMapper(RequisitionMapper.class);

    Requisition reqToReqDTO(Requisition requisition);
}
