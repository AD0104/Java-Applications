package com.itembox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembox.entities.LocationInfo;

public interface LocationInfoJpaRepository extends JpaRepository<LocationInfo, Integer> {

    
}
