package com.vaer.springdemo.dao;

import com.vaer.springdemo.entity.Customer;
import com.vaer.springdemo.util.SortUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    /* class fields */
    @Autowired
    private SessionFactory sessionFactory;

    /* override interface methods */
    @Override
    public List<Customer> getCustomers(int theSortField) {
        /** create session using sessionFactory*/
        Session session = sessionFactory.getCurrentSession();

        /** create query to get all customers from DB*/
        //List<Customer> customers = session.createQuery("from Customer order by lastName", Customer.class).getResultList();

        /** determine sort field*/
        String theFieldName = null;

        switch (theSortField) {
            case SortUtil.FIRST_NAME:
                theFieldName = "firstName";
                break;
            case SortUtil.LAST_NAME:
                theFieldName = "lastName";
                break;
            case SortUtil.EMAIL:
                theFieldName = "email";
                break;
            default:
                // if nothing matches the default to sort by lastName
                theFieldName = "lastName";
        }

        /** create a query*/
        String queryString = "from Customer order by " + theFieldName;
        Query<Customer> theQuery = session.createQuery(queryString, Customer.class);

        /** execute query and get result list*/
        List<Customer> customers = theQuery.getResultList();

        /** return the results*/
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        /** create session using sessionFactory*/
        Session session = sessionFactory.getCurrentSession();

        /** save customer to DB*/
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        /** create session using sessionFactory*/
        Session session = sessionFactory.getCurrentSession();

        /** get customer from DB by id*/
        Customer customer = session.get(Customer.class, id);

        /** return result*/
        return customer;
    }

    @Override
    public void delete(int id) {
        /** create session using sessionFactory*/
        Session session = sessionFactory.getCurrentSession();

        /** here we make query using HQL lang*/
        Query theQuery = session.createQuery("delete from Customer where id=:customerId");
        //Customer customer = new Customer();
        //customer.setId(id);

        /** here we set param to query object to use it in query text HQL lang*/
        theQuery.setParameter("customerId", id);

        /** execute query*/
        theQuery.executeUpdate();
        //session.delete(customer);

        /** print the result*/
        System.out.println("The customer with id=" + id + " was successful deleted!");
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        /** create session using sessionFactory*/
        Session session = sessionFactory.getCurrentSession();

        Query theQuery = null;

        /** only search by name if theSearchName is not empty*/
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            /** search for firstName or lastName ... case insensitive*/
            theQuery =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            /** theSearchName is empty ... so just get all customers*/
            theQuery =session.createQuery("from Customer", Customer.class);
        }

        /** execute query and get result list*/
        List<Customer> customers = theQuery.getResultList();

        /** return the results */
        return customers;
    }

}
