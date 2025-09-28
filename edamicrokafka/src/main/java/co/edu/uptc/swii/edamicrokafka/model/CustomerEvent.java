package co.edu.uptc.swii.edamicrokafka.model;

public class CustomerEvent {
    private CustomerEventType eventType;
    private String data;


    public CustomerEventType getEventType() { return eventType; }
    public void setEventType(CustomerEventType eventType) { this.eventType = eventType; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
