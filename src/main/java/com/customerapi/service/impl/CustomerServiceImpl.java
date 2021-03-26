package com.customerapi.service.impl;

import com.customerapi.controller.dto.CustomerUpdateDto;
import com.customerapi.exceptions.BusinessException;
import com.customerapi.exceptions.CustomerNotFundException;
import com.customerapi.model.Customer;
import com.customerapi.repository.CustomerRepository;
import com.customerapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        if(!repository.findByCpf(customer.getCpf()).isPresent())
            return repository.save(customer);
        throw new ResponseStatusException(HttpStatus.OK, "CUSTOMER ALREADY EXIST");
    }

    @Override
    public Customer getByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(()-> new CustomerNotFundException("CANOT GET A CUSTOMER THAT IS WHY DOES NOT EXIST"));
    }

    @Override
    public void delete(String cpf) {
        repository.findByCpf(cpf)
                .map(customer -> {
                    repository.deleteById(customer.getId());
                    return customer;
                }).orElseThrow(()-> new CustomerNotFundException("CANNOT DELETE CUSTOMER THAT IS WHY DOES NOT EXIST"));
    }

    @Override
    public Customer update(String cpf, CustomerUpdateDto newCustomer) {
        return repository.findByCpf(cpf)
                .map(customer -> {
                    customer.setAddress(newCustomer.getAddress());
                    customer.setCellPhone(newCustomer.getCellPhone());
                    return repository.save(customer);
                }).orElseThrow(()-> new CustomerNotFundException("CANNOT UPDATE CUSTOMER THAT IS WHY DOES NOT EXIST"));
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
