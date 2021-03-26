package com.customerapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CPF(message = "{invalid-cpf}")
    @NotEmpty(message = "{required-cpf}")
    private String cpf;

    @NotEmpty(message = "{required-name}")
    private String name;

    @NotNull(message = "{required-age}")
    private Integer age;

    @NotEmpty(message = "{required-address}")
    private String address;

    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "{only-numbers-cellphone}")
    @Length(min = 10, max = 11, message = "{invalid-cellPhone}")
    @NotEmpty(message = "{required-cellPhone}")
    private String cellPhone;
}
