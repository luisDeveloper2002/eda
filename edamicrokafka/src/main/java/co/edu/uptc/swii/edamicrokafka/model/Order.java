package co.edu.uptc.swii.edamicrokafka.model;
import jakarta.persistence.*;
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @Column(name = "orderid")
    private Long orderId;
    
    @Column(name = "customerid")
    private String customerId;
    
    @Column(name = "status")
    private String status;

    
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
