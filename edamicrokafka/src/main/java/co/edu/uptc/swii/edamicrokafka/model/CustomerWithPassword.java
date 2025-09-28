package co.edu.uptc.swii.edamicrokafka.model;
public class CustomerWithPassword {
        private Customer customer;
        private String password;

        public Customer getCustomer() { return customer; }
        public void setCustomer(Customer customer) { this.customer = customer; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }