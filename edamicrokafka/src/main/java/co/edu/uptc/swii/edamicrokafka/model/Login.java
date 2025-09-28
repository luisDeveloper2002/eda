package co.edu.uptc.swii.edamicrokafka.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "customerid")
    private String customerId;
    
    @Column(name = "password")
    private String password;

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
