package co.edu.uptc.swii.edamicrokafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import co.edu.uptc.swii.edamicrokafka.model.*;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "order_events";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private JsonUtils jsonUtils = new JsonUtils();

    private void sendOrderEvent(OrderEventType eventType, String data) {
        OrderEvent event = new OrderEvent();
        event.setEventType(eventType);
        event.setData(data);
        kafkaTemplate.send(TOPIC, jsonUtils.toJson(event));
    }

    public void sendAddOrderEvent(Order order) {
        sendOrderEvent(OrderEventType.ADD_ORDER, jsonUtils.toJson(order));
    }

    public void sendEditOrderEvent(Order order) {
        sendOrderEvent(OrderEventType.EDIT_ORDER, jsonUtils.toJson(order));
    }

    public void sendDeleteOrderEvent(String orderId) {
        sendOrderEvent(OrderEventType.DELETE_ORDER, orderId);
    }

    public void sendFindByOrderIDEvent(String orderId) {
        sendOrderEvent(OrderEventType.FIND_ORDER_BY_ID, orderId);
    }

    public void sendFindAllOrdersEvent() {
        sendOrderEvent(OrderEventType.FIND_ALL_ORDERS, "");
    }
}