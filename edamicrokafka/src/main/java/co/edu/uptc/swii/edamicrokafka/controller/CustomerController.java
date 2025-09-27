package co.edu.uptc.swii.edamicrokafka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.swii.edamicrokafka.model.Customer;
import co.edu.uptc.swii.edamicrokafka.service.CustomerEventProducer;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@RestController
public class CustomerController {

    @Autowired
    private CustomerEventProducer customerEventProducer;
    private static JsonUtils jsonUtils= new JsonUtils();

    @PostMapping("/addcustomer")
    public String sendMessageAddCustomer(@RequestBody String customer) {
        Customer customerObi = new Customer();
        customerObi = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendAddCustomerEvent(customerObi);
        return customerEventProducer.toString();
    }

    @PostMapping("/editcustomer")
    public String sendMessageEditCustomer(@RequestBody String customer) {
        Customer customerObi = new Customer();
        customerObi = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendEditCustomerEvent(customerObi);
        return customerEventProducer.toString();
    }
}
