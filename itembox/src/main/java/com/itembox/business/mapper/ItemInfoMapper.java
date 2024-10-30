package com.itembox.business.mapper;

import org.mapstruct.Mapper;
import com.itembox.dto.ItemInfoDto;
import com.itembox.entities.ItemInfo;

@Mapper(componentModel = "spring")
public interface ItemInfoMapper {
    ItemInfoDto toDto(ItemInfo itemInfo);
    ItemInfo toEntity(ItemInfoDto itemInfoDto);
}
