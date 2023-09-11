package com.sunbaseass.Service;

import com.sunbaseass.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Optional<Customer> createCustomer(Customer customer) throws Exception;
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
     Page<Customer> getCustomerByPaginate(int currentPage, int size);
}
