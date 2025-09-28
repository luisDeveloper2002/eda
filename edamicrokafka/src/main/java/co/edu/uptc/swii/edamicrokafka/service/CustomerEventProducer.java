package co.edu.uptc.swii.edamicrokafka.service;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import co.edu.uptc.swii.edamicrokafka.model.Customer;
import co.edu.uptc.swii.edamicrokafka.model.CustomerEvent;
import co.edu.uptc.swii.edamicrokafka.model.CustomerEventType;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;


@Service
public class CustomerEventProducer {
    private static final String TOPIC = "customer_events";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private JsonUtils jsonUtils = new JsonUtils();

    private void sendCustomerEvent(CustomerEventType eventType, String data) {
        CustomerEvent event = new CustomerEvent();
        event.setEventType(eventType);
        event.setData(data);
        kafkaTemplate.send(TOPIC, jsonUtils.toJson(event));
    }

    public void sendAddCustomerEvent(Customer customer, String password) {
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("customer", customer);
        customerData.put("password", password);
        sendCustomerEvent(CustomerEventType.ADD_CUSTOMER, jsonUtils.toJson(customerData));
    }

    public void sendEditCustomerEvent(Customer customer) {
        sendCustomerEvent(CustomerEventType.EDIT_CUSTOMER, jsonUtils.toJson(customer));
    }

    public void sendDeleteCustomerEvent(String document) {
        sendCustomerEvent(CustomerEventType.DELETE_CUSTOMER, document);
    }

    public void sendFindByCustomerIDEvent(String document) {
        sendCustomerEvent(CustomerEventType.FIND_CUSTOMER_BY_ID, document);
    }

    public void sendFindAllCustomersEvent() {
        sendCustomerEvent(CustomerEventType.FIND_ALL_CUSTOMERS, "");
    }
}

