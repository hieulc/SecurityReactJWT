package hieulc.securityreactjwt.service;

import hieulc.securityreactjwt.entity.Customer;
import hieulc.securityreactjwt.entity.UserRole;
import hieulc.securityreactjwt.repo.CustomerRepo;
import hieulc.securityreactjwt.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor @Slf4j
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final CustomerRepo customerRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByUsername(username);
        if (customer == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.error("User found in the database, " + username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        customer.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(customer.getUsername(), customer.getPassword(), authorities);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
    }

    @Override
    public UserRole save(UserRole role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToCustomer(String username, String roleName) {
        Customer customer = customerRepo.findByUsername(username);
        UserRole role = roleRepo.findByName(roleName);
        customer.getRoles().add(role);
        customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerBy(String username) {
        return customerRepo.findByUsername(username);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }


}
