package com.itembox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itembox.business.mapper.UserInfoMapper;
import com.itembox.dao.UserInfoJpaRepository;
import com.itembox.dto.UserInfoDto;
import com.itembox.entities.UserInfo;

@RestController
@RequestMapping("/item")
public class ItemRestController {
    private static final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    @Autowired
    protected UserInfoJpaRepository userInfoJpaRepository;

    @Autowired
    protected UserInfoMapper userInfoMapper;

    @GetMapping
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestParam String userName) {
        logger.info("[ItemRestController.getUserInfo] Ini Call");

        UserInfo userInfo = userInfoJpaRepository.findByUserName(userName);
        logger.info("UserInfo: {}", userInfo);
        UserInfoDto userInfoDto = userInfoMapper.toDto(userInfo);
        logger.info("UserInfoDto: {}", userInfoDto);

        logger.info("[ItemRestController.getUserInfo] Fin Call");
        return new ResponseEntity<>(HttpStatus.OK);
    
    }
}
