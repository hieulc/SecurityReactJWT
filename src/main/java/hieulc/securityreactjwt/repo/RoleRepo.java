package hieulc.securityreactjwt.repo;

import hieulc.securityreactjwt.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
