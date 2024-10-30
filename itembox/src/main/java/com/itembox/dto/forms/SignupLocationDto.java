package com.itembox.dto.forms;

import java.io.Serializable;
import java.util.List;

import com.itembox.dto.ItemInfoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignupLocationDto implements Serializable{

    @NotEmpty(message = "El campo no puede estar vacío")
    @NotBlank(message = "El campo no puede estar vacío")
    private String locationId;

    @NotEmpty(message = "El campo no puede estar vacío")
    @NotBlank(message = "El campo no puede estar vacío")
    private String locationDescription;

    private List<ItemInfoDto> items;
    
}
