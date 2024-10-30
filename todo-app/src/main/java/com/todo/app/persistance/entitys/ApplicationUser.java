package com.todo.app.persistance.entitys;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class ApplicationUser {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    protected UUID id;

    @Column(name = "name")
    protected String userName;

    @Column(name = "password")
    protected String userPassword;

    protected String roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ApplicationUserTask> tasks;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "ApplicationUser [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", roles="
                + roles + "]";
    }

    public ApplicationUser() {}

    public ApplicationUser(String userName, String userPassword, String roles) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.roles = roles;
    }
    
}
