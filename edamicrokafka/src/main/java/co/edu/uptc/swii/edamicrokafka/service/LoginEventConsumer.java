package co.edu.uptc.swii.edamicrokafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import co.edu.uptc.swii.edamicrokafka.model.Login;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@Service
public class LoginEventConsumer {

    @Autowired
    private LoginService loginService;

    @KafkaListener(topics = "addlogin_events", groupId = "login_group")
    public void handleAddLoginEvent(String login) {
        JsonUtils jsonUtils = new JsonUtils();
        Login receiveAddLogin = jsonUtils.fromJson(login, Login.class);
        loginService.save(receiveAddLogin);
    }

    @KafkaListener(topics = "editlogin_events", groupId = "login_group")
    public void handleEditLoginEvent(String login) {
        JsonUtils jsonUtils = new JsonUtils();
        Login receiveEditLogin = jsonUtils.fromJson(login, Login.class);
        loginService.save(receiveEditLogin);
    }

    @KafkaListener(topics = "findloginbyid_events", groupId = "login_group")
    public Login handleFindLoginByIDEvent(String id) {
        Login loginReceived = loginService.findById(Integer.parseInt(id));
        return loginReceived;
    }

    @KafkaListener(topics = "findalllogins_events", groupId = "login_group")
    public List<Login> handleFindAllLogins() {
        List<Login> loginsReceived = loginService.findAll();
        return loginsReceived;
    }

    @KafkaListener(topics = "deletelogin_events", groupId = "login_group")
    public void handleDeleteLoginEvent(String id) {
        Login login = loginService.findById(Integer.parseInt(id));
        if (login != null) {
            loginService.delete(login);
        }
    }
}

