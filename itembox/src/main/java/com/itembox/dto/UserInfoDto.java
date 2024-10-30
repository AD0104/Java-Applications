package com.itembox.dto;

import java.io.Serializable;
import java.util.List;

import com.itembox.entities.UserInfoAuthorities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoDto implements Serializable {
    protected Integer id;
    protected String userName;
    protected String userPassword;
    protected String roles;
    protected List<UserInfoAuthorities> authorities;
}
