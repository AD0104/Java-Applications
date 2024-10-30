package com.itembox.business.mapper;

import org.mapstruct.Mapper;

import com.itembox.dto.LocationInfoDto;
import com.itembox.entities.LocationInfo;


@Mapper(componentModel = "spring")
public interface LocationInfoMapper {

    LocationInfoDto toDto(LocationInfo location);
    LocationInfo toEntity(LocationInfoDto locationInfoDto);
}
