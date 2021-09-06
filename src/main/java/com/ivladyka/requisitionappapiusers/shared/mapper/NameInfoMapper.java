package com.ivladyka.requisitionappapiusers.shared.mapper;

import com.ivladyka.requisitionappapiusers.model.requisition.NameInfo;
import com.ivladyka.requisitionappapiusers.shared.NameInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NameInfoMapper {

    NameInfoMapper MAPPER = Mappers.getMapper(NameInfoMapper.class);


    NameInfoDTO nameInfoToNameInfoDTO(NameInfo nameInfo);
}
