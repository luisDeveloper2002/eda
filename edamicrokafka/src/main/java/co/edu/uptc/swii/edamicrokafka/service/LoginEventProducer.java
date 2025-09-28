package co.edu.uptc.swii.edamicrokafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import co.edu.uptc.swii.edamicrokafka.model.*;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@Service
public class LoginEventProducer {
    private static final String TOPIC = "login_events";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private JsonUtils jsonUtils = new JsonUtils();

    private void sendLoginEvent(LoginEventType eventType, String data) {
        LoginEvent event = new LoginEvent();
        event.setEventType(eventType);
        event.setData(data);
        kafkaTemplate.send(TOPIC, jsonUtils.toJson(event));
    }

    public void sendAddLoginEvent(Login login) {
        sendLoginEvent(LoginEventType.ADD_LOGIN, jsonUtils.toJson(login));
    }

    public void sendEditLoginEvent(Login login) {
        sendLoginEvent(LoginEventType.EDIT_LOGIN, jsonUtils.toJson(login));
    }

    public void sendDeleteLoginEvent(String id) {
        sendLoginEvent(LoginEventType.DELETE_LOGIN, id);
    }

    public void sendFindByLoginIDEvent(String id) {
        sendLoginEvent(LoginEventType.FIND_LOGIN_BY_ID, id);
    }

    public void sendFindAllLoginsEvent() {
        sendLoginEvent(LoginEventType.FIND_ALL_LOGINS, "");
    }
}