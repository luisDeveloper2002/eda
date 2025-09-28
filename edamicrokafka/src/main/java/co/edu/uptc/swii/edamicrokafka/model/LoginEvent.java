package co.edu.uptc.swii.edamicrokafka.model;

public class LoginEvent {
    private LoginEventType eventType;
    private String data;

    public LoginEventType getEventType() { return eventType; }
    public void setEventType(LoginEventType eventType) { this.eventType = eventType; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
