package co.edu.uptc.swii.edamicrokafka.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import co.edu.uptc.swii.edamicrokafka.model.Customer;
import co.edu.uptc.swii.edamicrokafka.model.CustomerEvent;
import co.edu.uptc.swii.edamicrokafka.model.CustomerWithPassword;
import co.edu.uptc.swii.edamicrokafka.model.Login;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;



@Service
public class CustomerEventConsumer {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginService loginService;
    private JsonUtils jsonUtils = new JsonUtils();

    @KafkaListener(topics = "customer_events", groupId = "customer_group")
    public void handleCustomerEvents(String eventMessage) {
        CustomerEvent event = jsonUtils.fromJson(eventMessage, CustomerEvent.class);
        
        switch (event.getEventType()) {
            case ADD_CUSTOMER:
                Map<String, Object> data = jsonUtils.fromJson(event.getData(), Map.class);
                Customer customer = jsonUtils.fromJson(jsonUtils.toJson(data.get("customer")), Customer.class);
                String password = (String) data.get("password");
                
                customerService.save(customer);
                
                Login login = new Login();
                login.setCustomerId(customer.getDocument());
                login.setPassword(password);
                loginService.save(login);
                break;
                
            case EDIT_CUSTOMER:
                Customer customerToEdit = jsonUtils.fromJson(event.getData(), Customer.class);
                customerService.save(customerToEdit);
                break;
                
            case DELETE_CUSTOMER:
                String document = event.getData();
                Customer customerToDelete = customerService.findById(document);
                if (customerToDelete != null) {
                    customerService.delete(customerToDelete);
                }
                break;
                
            case FIND_CUSTOMER_BY_ID:
                String docToFind = event.getData();
                Customer foundCustomer = customerService.findById(docToFind);
                
                break;
                
            case FIND_ALL_CUSTOMERS:
                List<Customer> customers = customerService.findAll();
                break;
        }
    }
}
