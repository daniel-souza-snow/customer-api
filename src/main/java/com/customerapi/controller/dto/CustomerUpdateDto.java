package com.customerapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDto {
    @NotEmpty(message = "{required-address}")
    private String address;

    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "{only-numbers-cellphone}")
    @Length(min = 10, max = 11, message = "{invalid-cellPhone}")
    @NotEmpty(message = "{required-cellPhone}")
    private String cellPhone;
}
