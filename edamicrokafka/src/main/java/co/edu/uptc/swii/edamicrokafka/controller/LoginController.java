package co.edu.uptc.swii.edamicrokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uptc.swii.edamicrokafka.model.Login;
import co.edu.uptc.swii.edamicrokafka.service.LoginEventProducer;
import co.edu.uptc.swii.edamicrokafka.utils.JsonUtils;

@RestController
public class LoginController {
    @Autowired
    private LoginEventProducer loginEventProducer;
    private JsonUtils jsonUtils = new JsonUtils();

   @PostMapping("/addLogin")
    public String createLogin(@RequestBody String loginJson) {
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        loginEventProducer.sendAddLoginEvent(login);
        return "Login creation request sent";
    }

    @PostMapping("/editLogin")
    public String updateLogin(@RequestBody String loginJson) {
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        loginEventProducer.sendEditLoginEvent(login);
        return "Login update request sent";
    }
    @DeleteMapping("/deleteLogin/{id}")
    public String deleteLogin(@PathVariable String id) {
        loginEventProducer.sendDeleteLoginEvent(id);
        return "Login deletion request sent";
    }
   @GetMapping("/login/{customerId}")
    public String findLogin(@PathVariable String customerId) {
        loginEventProducer.sendFindByLoginIDEvent(customerId);
        return "Login find request sent";
    }
       @GetMapping("/logins")
    public String findAllOrders() {
        loginEventProducer.sendFindAllLoginsEvent();
        return "Find all orders request sent";
    }
}
