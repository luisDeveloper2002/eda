package co.edu.uptc.swii.edamicrokafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import co.edu.uptc.swii.edamicrokafka.model.Order;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@Service
public class OrderEventProducer {

    private static final String TOPIC_ADD = "addorder_events";
    private static final String TOPIC_EDIT = "editorder_events";
    private static final String TOPIC_FINDBYID = "findorderbyid_events";
    private static final String TOPIC_FINDALL = "findallorders_events";
    private static final String TOPIC_DELETE = "deleteorder_events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateAdd;
    public void sendAddOrderEvent(Order order){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json = jsonUtils.toJson(order);
        kafkaTemplateAdd.send(TOPIC_ADD, json);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateEdit;
    public void sendEditOrderEvent(Order order){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json = jsonUtils.toJson(order);
        kafkaTemplateEdit.send(TOPIC_EDIT, json);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateFindById;
    public void sendFindByOrderIDEvent(String orderid){
        kafkaTemplateFindById.send(TOPIC_FINDBYID, orderid);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateFindAll;
    public void sendFindAllOrdersEvent(){
        kafkaTemplateFindAll.send(TOPIC_FINDALL, "FIND_ALL");
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateDelete;
    public void sendDeleteOrderEvent(String orderid) {
        kafkaTemplateDelete.send(TOPIC_DELETE, orderid);
    }
}
