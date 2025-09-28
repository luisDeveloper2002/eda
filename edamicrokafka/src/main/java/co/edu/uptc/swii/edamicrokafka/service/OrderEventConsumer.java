package co.edu.uptc.swii.edamicrokafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import co.edu.uptc.swii.edamicrokafka.model.Order;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@Service
public class OrderEventConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "addorder_events", groupId = "order_group")
    public void handleAddOrderEvent(String order) {
        JsonUtils jsonUtils = new JsonUtils();
        Order receiveAddOrder = jsonUtils.fromJson(order, Order.class);
        orderService.save(receiveAddOrder);
    }

    @KafkaListener(topics = "editorder_events", groupId = "order_group")
    public void handleEditOrderEvent(String order) {
        JsonUtils jsonUtils = new JsonUtils();
        Order receiveEditOrder = jsonUtils.fromJson(order, Order.class);
        orderService.save(receiveEditOrder);
    }

    @KafkaListener(topics = "findorderbyid_events", groupId = "order_group")
    public Order handleFindOrderByIDEvent(String orderid) {
        Order orderReceived = orderService.findById(Long.parseLong(orderid));
        return orderReceived;
    }

    @KafkaListener(topics = "findallorders_events", groupId = "order_group")
    public List<Order> handleFindAllOrders() {
        List<Order> ordersReceived = orderService.findAll();
        return ordersReceived;
    }

    @KafkaListener(topics = "deleteorder_events", groupId = "order_group")
    public void handleDeleteOrderEvent(String orderid) {
        Order order = orderService.findById(Long.parseLong(orderid));
        if (order != null) {
            orderService.delete(order);
        }
    }
}

