package com.customerapi.controller;

import com.customerapi.model.Customer;
import com.customerapi.repository.CustomerRepository;
import com.customerapi.util.CustomerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    private String CUSTOMER_PATH = "/customers";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CustomerUtil util;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("must to create a new customer successfully")
    public void createCustomerTest() throws Exception {
        Customer customerTest = util.createNewCustomer("57448545094");

        String json = new ObjectMapper().writeValueAsString(customerTest);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(CUSTOMER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc
                .perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value(customerTest.getCpf()))
                .andExpect(jsonPath("cellPhone").value(customerTest.getCellPhone()))
                .andExpect(jsonPath("name").value(customerTest.getName()))
                .andExpect(jsonPath("address").value(customerTest.getAddress()))
                .andExpect(jsonPath("age").value(customerTest.getAge()))
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    @DisplayName("must to return bad request(invalid data)")
    public void createCustomer400Test() throws Exception{
        Customer customerTest = util.createNewCustomer("57448545094");
        //invalid cpf
        customerTest.setCpf("101020202020");
        String json = new ObjectMapper().writeValueAsString(customerTest);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(CUSTOMER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc
                .perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("must to return an existing customer successfully")
    public void getCustomerTest() throws Exception{
        Customer customerTest = util.createNewCustomer("87235841060");
        customerRepository.save(customerTest);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(CUSTOMER_PATH+"/{cpf}", customerTest.getCpf());
        mvc
                .perform(mockRequest)
                .andExpect(jsonPath("cpf").value(customerTest.getCpf()))
                .andExpect(jsonPath("cellPhone").value(customerTest.getCellPhone()))
                .andExpect(jsonPath("name").value(customerTest.getName()))
                .andExpect(jsonPath("address").value(customerTest.getAddress()))
                .andExpect(jsonPath("age").value(customerTest.getAge()))
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("must return NotFound passing cpf valid but not saved ")
    public void getCustomerNotFoundTest() throws Exception{

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(CUSTOMER_PATH+"/{cpf}", "91415882070");
        mvc
                .perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("must to delete an existing customer successfully")
    public void deleteCustomerTest() throws Exception{
        Customer customerTest = util.createNewCustomer("13570253007");
        customerRepository.save(customerTest);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete(CUSTOMER_PATH+"/{cpf}", customerTest.getCpf());
        mvc
                .perform(mockRequest)
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("must return NotFound passing cpf valid but not saved")
    public void deleteCustomerNotFoundTest() throws Exception{
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete(CUSTOMER_PATH+"/51908437006");
        mvc
                .perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("must to update an existing customer successfully")
    public void updateCustomer() throws Exception{
        Customer customerTest = util.createNewCustomer("43008142010");
        customerRepository.save(customerTest);
        customerTest.setAddress("Rua Brazil Update, 112");
        customerTest.setCellPhone("11777777777");

        String json = new ObjectMapper().writeValueAsString(customerTest);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put(CUSTOMER_PATH+"/43008142010")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc
                .perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value(customerTest.getCpf()))
                .andExpect(jsonPath("cellPhone").value("11777777777"))
                .andExpect(jsonPath("name").value(customerTest.getName()))
                .andExpect(jsonPath("address").value("Rua Brazil Update, 112"))
                .andExpect(jsonPath("age").value(customerTest.getAge()))
                .andExpect(jsonPath("id").value(customerTest.getId()));
    }

}
