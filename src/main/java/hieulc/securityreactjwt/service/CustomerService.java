package hieulc.securityreactjwt.service;

import hieulc.securityreactjwt.entity.Customer;
import hieulc.securityreactjwt.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer save(Customer customer);
    UserRole save(UserRole role);
    void addRoleToCustomer(String username, String roleName);
    Customer getCustomerBy(String username);
    List<Customer> getCustomers();

}
