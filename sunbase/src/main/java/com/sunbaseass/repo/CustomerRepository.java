package com.sunbaseass.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbaseass.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

