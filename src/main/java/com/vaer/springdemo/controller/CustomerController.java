package com.vaer.springdemo.controller;

import com.vaer.springdemo.entity.Customer;
import com.vaer.springdemo.service.CustomerService;
import com.vaer.springdemo.util.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    /**
     * TO RUN listCustomer controller type in browser http://localhost:8080/webcustomertracker/customer/list
     * */

    /* class fields */
    @Autowired
    private CustomerService customerService;

    /* methods */

    //old version of listCustomers method
    /*@GetMapping("/list")
    public String listCustomers(Model model){
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("theCustomers", customers);
        return "list-customers";
    }*/

    @GetMapping("/list")
    public String listCustomers(Model Model, @RequestParam(required=false) String sort) {

        /** get customers from the service*/
        List<Customer> customers = null;

        /** check for sort field*/
        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            customers = customerService.getCustomers(theSortField);
        }
        else {
            // no sort field provided ... default to sorting by last name
            customers = customerService.getCustomers(SortUtil.LAST_NAME);
        }

        /** add the customers to the model*/
        Model.addAttribute("theCustomers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("theCustomer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("theCustomer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model){

        /** get customer from DB using customerId param*/
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("theCustomer", customer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id){

        /** delete customer from DB using id */
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model model){
        /** first we need to search customer in DB*/
        List<Customer> customers = customerService.searchCustomers(theSearchName);

        /** then we need to add customers to model*/
        model.addAttribute("theCustomers", customers);

        return "list-customers";
    }
}
