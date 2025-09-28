package co.edu.uptc.swii.edamicrokafka.model;

public class OrderEvent {
    private OrderEventType eventType;
    private String data;

    public OrderEventType getEventType() { return eventType; }
    public void setEventType(OrderEventType eventType) { this.eventType = eventType; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
