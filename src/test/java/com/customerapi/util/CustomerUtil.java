package com.customerapi.util;

import com.customerapi.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtil {

    public Customer createNewCustomer(String cpf) {
        return Customer.builder()
                .cpf(cpf)
                .name("Maria Teste")
                .age(88)
                .cellPhone("11999999999")
                .address("street Teste, 123")
                .build();
    }
}
