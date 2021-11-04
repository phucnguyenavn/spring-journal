package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springutility.model.account.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = "Select u.id  from  User u where u.email =?1")
    Integer findIdByEmail(String email);
}
