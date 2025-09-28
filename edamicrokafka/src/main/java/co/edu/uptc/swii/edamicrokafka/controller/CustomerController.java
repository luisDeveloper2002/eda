package co.edu.uptc.swii.edamicrokafka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.swii.edamicrokafka.model.Customer;
import co.edu.uptc.swii.edamicrokafka.model.CustomerWithPassword;
import co.edu.uptc.swii.edamicrokafka.service.CustomerEventProducer;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@RestController
public class CustomerController {

    @Autowired
    private CustomerEventProducer customerEventProducer;
    private static JsonUtils jsonUtils= new JsonUtils();

    @PostMapping("/addcustomer")
    public String sendMessageAddCustomer(@RequestBody String customerData) {
        CustomerWithPassword customerWithPass = new CustomerWithPassword();
          customerWithPass = jsonUtils.fromJson(customerData, CustomerWithPassword.class);
        customerEventProducer.sendAddCustomerEvent(customerWithPass.getCustomer(), customerWithPass.getPassword());
        return "Customer creation request sent";
    }

    @PostMapping("/editcustomer")
    public String sendMessageEditCustomer(@RequestBody String customer) {
        Customer customerObi = new Customer();
        customerObi = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendEditCustomerEvent(customerObi);
        return customerEventProducer.toString();
    }
    @DeleteMapping("/deletecustomer/{document}")
public String deleteCustomer(@PathVariable String document) {
    customerEventProducer.sendDeleteCustomerEvent(document);
    return "Delete request sent";
}

@GetMapping("/customer/{document}")
public String findCustomerById(@PathVariable String document) {
    
    customerEventProducer.sendFindByCustomerIDEvent(document);
    return "Find by ID request sent";
}

@GetMapping("/customers")
public String findAllCustomers() {
    customerEventProducer.sendFindAllordersEvent();
    return "Find all customers request sent";
}
}
