package com.customerapi.controller;

import com.customerapi.controller.dto.CustomerUpdateDto;
import com.customerapi.model.Customer;
import com.customerapi.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @ApiOperation("CREATE A NEW CUSTOMER")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Create customer request successfully"),
            @ApiResponse(code = 400, message = "Any customer data passed in the request is empty or invalid")
    })
    @PostMapping
    public Customer createNewCustomer(@RequestBody @Valid Customer customer){
        return service.save(customer);
    }

    @ApiOperation("GET A CUSTOMER")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Find customer request successfully"),
            @ApiResponse(code = 404, message = "The customer was not found")
    })
    @GetMapping("{cpf}")
    public Customer getCustomerByCpf(@PathVariable String cpf){
        return service.getByCpf(cpf);
    }

    @ApiOperation("DELETE A CUSTOMER")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete customer request successfully"),
            @ApiResponse(code = 404, message = "The customer was not found")
    })
    @DeleteMapping("{cpf}")
    public void deleteCustomerByCpf(@PathVariable String cpf){
        service.delete(cpf);
    }

    @ApiOperation("UPDATE A CUSTOMER")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update customer request successfully"),
            @ApiResponse(code = 400, message = "Any customer data passed in the request is empty or invalid"),
            @ApiResponse(code = 404, message = "The customer was not found")
    })
    @PutMapping("{cpf}")
    public Customer updateCustomer(@PathVariable String cpf, @RequestBody @Valid CustomerUpdateDto customer){
        return service.update(cpf,customer);
    }

    @ApiOperation("GET ALL CUSTOMERS")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Find All customers request successfully or Empty List")
    })
    @GetMapping
    public Page<Customer>getAllCustomers(Pageable pageable){
        return service.getAll(pageable);
    }


}
