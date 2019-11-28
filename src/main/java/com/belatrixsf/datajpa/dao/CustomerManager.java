package com.belatrixsf.datajpa.dao;

import com.belatrixsf.datajpa.entity.Customer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager {

  private final CustomerRepository repository;


  public CustomerManager(CustomerRepository repository) {
    this.repository = repository;
  }


  public Customer findCustomerById(long id) {
    return repository.findById(id);
  }

  public List<Customer> findCustomersByLastName(String lastName) {
    return repository.findByLastName(lastName);
  }

  public long addNewCustomer(Customer newCustomer) {
    Customer savedCustomer = repository.save(newCustomer);
    return savedCustomer.getId();
  }

}
