package com.itembox.business.mapper;

import org.mapstruct.Mapper;

import com.itembox.dto.UserInfoDto;
import com.itembox.entities.UserInfo;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    UserInfoDto toDto(UserInfo userInfo);
    UserInfo toEntity(UserInfoDto userInfoDto);
}
