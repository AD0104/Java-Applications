package com.itembox.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDto implements Serializable {
    protected Integer id;
    protected String itemName;
    protected String itemDescription;
    protected String itemImagePath;
    protected Integer itemStock;
    protected Float itemSingleValue;
    private LocationInfoDto location;
}
