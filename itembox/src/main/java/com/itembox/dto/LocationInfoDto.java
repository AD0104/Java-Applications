package com.itembox.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationInfoDto {
    private Integer id;
    private String locationId;
    private String locationDescription;
    private List<ItemInfoDto> items;
}
