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

@Entity
@Table(name = "tbl_user_authorities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAuthorities {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "usr_auth_id")
    private Integer id;

    @Column(name = "usr_auth_authorities")
    private String authorities;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usr_inf_id", nullable = false)
    private UserInfo user;

}
