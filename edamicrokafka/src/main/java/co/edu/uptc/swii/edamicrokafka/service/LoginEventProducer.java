package co.edu.uptc.swii.edamicrokafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import co.edu.uptc.swii.edamicrokafka.model.Login;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@Service
public class LoginEventProducer {
    private static final String TOPIC_ADD = "addlogin_events";
    private static final String TOPIC_EDIT = "editlogin_events";
    private static final String TOPIC_FINDBYID = "findloginbyid_events";
    private static final String TOPIC_FINDALL = "findalllogins_events";
    private static final String TOPIC_DELETE = "deletelogin_events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateAdd;
    public void sendAddLoginEvent(Login login){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json = jsonUtils.toJson(login);
        kafkaTemplateAdd.send(TOPIC_ADD, json);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateEdit;
    public void sendEditLoginEvent(Login login){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json = jsonUtils.toJson(login);
        kafkaTemplateEdit.send(TOPIC_EDIT, json);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateFindById;
    public void sendFindByLoginIDEvent(String id){
        kafkaTemplateFindById.send(TOPIC_FINDBYID, id);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateFindAll;
    public void sendFindAllLoginsEvent(){
        kafkaTemplateFindAll.send(TOPIC_FINDALL, "FIND_ALL");
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateDelete;
    public void sendDeleteLoginEvent(String id) {
        kafkaTemplateDelete.send(TOPIC_DELETE, id);
    }
}

