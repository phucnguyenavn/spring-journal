package personal.springjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.springjournal.model.account.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(String role);
}
