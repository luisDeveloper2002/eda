package co.edu.uptc.swii.edamicrokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uptc.swii.edamicrokafka.model.Order;
import co.edu.uptc.swii.edamicrokafka.service.OrderEventProducer;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@RestController
public class OrderController {
    @Autowired
    private OrderEventProducer orderEventProducer;
    private JsonUtils jsonUtils = new JsonUtils();
   
     @PostMapping("/addOrder")
    public String createOrder(@RequestBody String orderJson) {
        Order order = jsonUtils.fromJson(orderJson, Order.class);
        orderEventProducer.sendAddOrderEvent(order);
        return "Order creation request sent";
    }

     @PostMapping("/editOrder")
    public String updateOrder(@RequestBody String orderJson) {
        Order order = jsonUtils.fromJson(orderJson, Order.class);
        orderEventProducer.sendEditOrderEvent(order);
        return "Order update request sent";
    }
    @DeleteMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable String orderId) {
        orderEventProducer.sendDeleteOrderEvent(orderId);
        return "Order deletion request sent";
    }
    @GetMapping("/order/{orderId}")
    public String findOrder(@PathVariable String orderId) {
        orderEventProducer.sendFindByOrderIDEvent(orderId);
        return "Order find request sent";
    }

    @GetMapping("/orders")
    public String findAllOrders() {
        orderEventProducer.sendFindAllOrdersEvent();
        return "Find all orders request sent";
    }
}
