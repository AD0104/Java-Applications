package com.itembox.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itembox.entities.UserInfo;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUserName(String userName);
}
