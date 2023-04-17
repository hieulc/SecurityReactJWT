package hieulc.securityreactjwt;

import hieulc.securityreactjwt.entity.Customer;
import hieulc.securityreactjwt.entity.UserRole;
import hieulc.securityreactjwt.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityReactJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityReactJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(CustomerService customerService) {
        return args -> {
            customerService.save(new UserRole(null, "ROLE_USER"));
            customerService.save(new UserRole(null, "ROLE_MANAGER"));
            customerService.save(new UserRole(null, "ROLE_ADMIN"));

            customerService.save(new Customer(null, "John Travolta", "john", "1234", new ArrayList<>()));
            customerService.save(new Customer(null, "Will Smith", "smith", "1234", new ArrayList<>()));
            customerService.save(new Customer(null, "Amber Heard", "amber", "1234", new ArrayList<>()));
            customerService.save(new Customer(null, "Johnny Depp", "depp", "1234", new ArrayList<>()));

            customerService.addRoleToCustomer("john", "ROLE_USER");
            customerService.addRoleToCustomer("smith", "ROLE_USER");
            customerService.addRoleToCustomer("amber", "ROLE_USER");
            customerService.addRoleToCustomer("amber", "ROLE_ADMIN");
        };
    }

}
