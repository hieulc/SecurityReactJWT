package hieulc.securityreactjwt.repo;

import hieulc.securityreactjwt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
