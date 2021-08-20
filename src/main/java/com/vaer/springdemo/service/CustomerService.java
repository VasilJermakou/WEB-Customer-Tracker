package com.vaer.springdemo.service;

import com.vaer.springdemo.entity.Customer;
import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(int theSortField);
    void saveCustomer(Customer customer);
    Customer getCustomer(int id);
    void delete(int id);
    List<Customer> searchCustomers(String theSearchName);
}
