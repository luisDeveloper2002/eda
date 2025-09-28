package co.edu.uptc.swii.edamicrokafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uptc.swii.edamicrokafka.model.Order;
import co.edu.uptc.swii.edamicrokafka.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
      @Autowired
    private OrderRepository orderRepository;

    public boolean save(Order order){
        boolean flag = false;
        Order o = orderRepository.saveAndFlush(order);
        if (o != null) flag = true;
        return flag;
    }

    public boolean delete(Order order){
        boolean flag = false;
        try{
            orderRepository.delete(order);
            flag = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public Order findById(Long orderid){
        Order order = null;
        Optional<Order> optionalOrder = orderRepository.findById(orderid);
        if(optionalOrder.isPresent()){
            order = optionalOrder.get();
        }
        return order;
    }

    public List<Order> findAll(){
        List<Order> listOrder = new ArrayList<Order>();
        Iterable<Order> orders = orderRepository.findAll();
        orders.forEach((o) -> {
            listOrder.add(o);
        });
        return listOrder;
    }
}
