package com.itembox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembox.entities.ItemInfo;

public interface ItemInfoJpaRepository extends JpaRepository<ItemInfo, Integer> {

}
