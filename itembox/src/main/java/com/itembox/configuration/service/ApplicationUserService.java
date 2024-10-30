package com.itembox.configuration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itembox.business.mapper.UserInfoMapper;
import com.itembox.dao.UserInfoJpaRepository;
import com.itembox.dto.UserInfoDto;
import com.itembox.entities.UserInfo;

@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    UserInfoJpaRepository userInfoJpaRepository;

    @Autowired
    UserInfoMapper userInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(ApplicationUserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String threadName = Thread.currentThread().getName();
        logger.info("[ApplicationUserService.loadUserByUsername] Ini Thread: {}", threadName);
        UserInfo userInfo = userInfoJpaRepository.findByUserName(username);
        UserInfoDto userInfoDto = userInfoMapper.toDto(userInfo);
        logger.info("UserInfo: {}", userInfo);
        logger.info("UserInfoDto: {}", userInfoDto);
        if (userInfoDto == null)
            throw new UsernameNotFoundException(username);
        logger.info("[ApplicationUserService.loadUserByUsername] Fin Thread: {}", threadName);

        return User.builder()
                .username(userInfoDto.getUserName())
                .password(userInfoDto.getUserPassword())
                .roles(this.getRoles(userInfoDto))
                .build();
    }

    private String[] getRoles(UserInfoDto user) {
        return user.getRoles().isEmpty() ? new String[] { "CLIENT" } : user.getRoles().split(",");
    }

}
