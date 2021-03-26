package com.customerapi.service;

import com.customerapi.controller.dto.CustomerUpdateDto;
import com.customerapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Customer save(Customer customer);

    Customer getByCpf(String cpf);

    void delete(String cpf);

    Customer update(String cpf, CustomerUpdateDto customer);

    Page<Customer> getAll(Pageable pageable);
}
