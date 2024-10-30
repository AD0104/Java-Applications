package com.itembox.dto.forms;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto implements Serializable {
    @NotEmpty(message = "Este campo no puede estar vacio")
    @NotBlank(message = "Este campo no puede estar vacio")
    private String userName;

    @NotEmpty(message = "Este campo no puede estar vacio")
    @NotBlank(message = "Este campo no puede estar vacio")
    private String password;

    @NotEmpty(message = "Este campo no puede estar vacio")
    @NotBlank(message = "Este campo no puede estar vacio")
    private String confirmPassword;

    public @NotEmpty(message = "Este campo no puede estar vacio") @NotBlank(message = "Este campo no puede estar vacio") String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "Este campo no puede estar vacio") @NotBlank(message = "Este campo no puede estar vacio") String userName) {
        this.userName = userName;
    }

    public @NotEmpty(message = "Este campo no puede estar vacio") @NotBlank(message = "Este campo no puede estar vacio") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Este campo no puede estar vacio") @NotBlank(message = "Este campo no puede estar vacio") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Este campo no puede estar vacio") @NotBlank(message = "Este campo no puede estar vacio") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotEmpty(message = "Este campo no puede estar vacio") @NotBlank(message = "Este campo no puede estar vacio") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public SignupDto() {
    }

    public SignupDto(String userName, String password, String confirmPassword) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
