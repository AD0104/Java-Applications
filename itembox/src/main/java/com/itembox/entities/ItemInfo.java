package com.itembox.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_item_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "itm_inf_id")
    private Integer id;

    @Column(name = "itm_inf_name", length = 50)
    private String itemName;

    @Column(name = "itm_inf_description", length = 100)
    private String itemDescription;

    @Column(name = "itm_inf_image_path", length = 255) 
    private String itemImagePath;

    @Column(name = "itm_inf_stock")
    private Integer itemStock;

    @Column(name = "itm_inf_unitary_price")
    private Float itemSingleValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "suc_id", nullable = false)
    private LocationInfo location;
}
