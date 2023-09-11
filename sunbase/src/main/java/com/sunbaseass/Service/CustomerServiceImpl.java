package com.sunbaseass.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sunbaseass.model.Customer;
import com.sunbaseass.repo.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Customer customer;
    private List<Customer> customerslist=new ArrayList<>();


    @Override
    public Customer getCustomerById(Long id) {
        // TODO Auto-generated method stub
        Customer myCustomer=null;
        myCustomer=customerslist.stream().filter(e ->e.getId()==id).findFirst().get();
        return  myCustomer;


    }

    @Override
    public Optional<Customer> createCustomer(Customer customer) throws Exception {
        Optional<Customer> local =this.customerRepository.findById(customer.getId());
        if(local!=null) {
            System.out.println("Customer  is aleready there!");
            throw new Exception("Customer already present");
        }
        else {
            customerslist.add(customer);
            customerRepository.save(customer);
        }

        return local;

    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }




    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }





    public Page<Customer> getCustomerByPaginate(int currentPage, int size) {
        Pageable p = PageRequest.of(currentPage, size);
        return customerRepository.findAll(p);
    }

}

