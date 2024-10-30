package com.itembox.entities;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "tbl_sucursales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "suc_id")
    private Integer id;

    @Column(name = "suc_id_external", nullable = false, length = 50)
    private String locationId;

    @Column(name = "suc_description", nullable = false, length = 100)
    private String locationDescription;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ItemInfo> items;
}
