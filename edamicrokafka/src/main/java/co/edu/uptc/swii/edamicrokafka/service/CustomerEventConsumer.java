package co.edu.uptc.swii.edamicrokafka.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import co.edu.uptc.swii.edamicrokafka.model.Customer;
import co.edu.uptc.swii.edamicrokafka.model.CustomerWithPassword;
import co.edu.uptc.swii.edamicrokafka.model.Login;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;



@Service
public class CustomerEventConsumer {

    @Autowired
    private CustomerService customerService;
   @Autowired
    private LoginService loginService;

    @KafkaListener(topics = "addcustomer_events", groupId = "customer_group")
    public void handleAddOrderEvent(String customerData) {
        JsonUtils jsonUtils = new JsonUtils();
         CustomerWithPassword customerWithPass = jsonUtils.fromJson(customerData, CustomerWithPassword.class);
        Customer customer = customerWithPass.getCustomer();
        customerService.save(customer);
         Login login = new Login();
        login.setCustomerId(customer.getDocument());
        login.setPassword(customerWithPass.getPassword());
        loginService.save(login);
    }

    @KafkaListener(topics = "editcustomer_events", groupId = "customer_group")
    public void handleEditCustomerEvent(String customer) {
        JsonUtils jsonUtils = new JsonUtils();
        Customer receiveEditCustomer = jsonUtils.fromJson(customer, Customer.class);
        customerService.save(receiveEditCustomer);
    }

    @KafkaListener(topics = "findcustomerbyid_events", groupId = "customer_group")
    public Customer handleFindCustomerByIDEvent(String customer) {
        Customer customerReceived = customerService.findById(customer);
        return customerReceived;
    }

    @KafkaListener(topics = "findallcustomers_events", groupId = "customer_group")
    public List<Customer> handleFindAllCustomers() {
        List<Customer> customersReceived = customerService.findAll();
        return customersReceived;
    }
    @KafkaListener(topics = "deletecustomer_events", groupId = "customer_group")
public void handleDeleteCustomerEvent(String document) {
    Customer customer = customerService.findById(document);
    if (customer != null) {
        customerService.delete(customer);
    }
}

}
