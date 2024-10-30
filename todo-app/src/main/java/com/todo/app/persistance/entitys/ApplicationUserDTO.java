package com.todo.app.persistance.entitys;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApplicationUserDTO {

    @NotBlank(message = "El Campo Nombre de Usuario no Puede Estar Vacío")
    @Size(min = 4, max = 50, message = "El Nombre de Usuario Debe Ser Mayor a 4 Caracteres y Menor a 50 Caracteres")
    protected String userName;

    @NotBlank(message = "El Campo Password No puede Estar Vacío")
    @Size(min = 8, max = 50, message = "El Password Debe Ser Mayor a 8 Caracteres y Menor a 50 Caracteres")
    protected String userPassword;

    @NotBlank(message = "El Campo Confirm Password no Puede Estar Vacío")
    protected String confirmPassword;

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
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
