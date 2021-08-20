package com.vaer.springdemo.service;

import com.vaer.springdemo.dao.CustomerDAO;
import com.vaer.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    /* class fields */
    @Autowired
    private CustomerDAO customerDAO;

    /* overriding interface methods */
    @Override
    @Transactional /*this annotation allows to open and close transactions automatically*/
    public List<Customer> getCustomers(int theSortField) {
        List<Customer> customers = customerDAO.getCustomers(theSortField);
        return customers;
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        Customer customer = customerDAO.getCustomer(id);
        return customer;
    }

    @Override
    @Transactional
    public void delete(int id) {
        customerDAO.delete(id);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {
        return customerDAO.searchCustomers(theSearchName);
    }


}
