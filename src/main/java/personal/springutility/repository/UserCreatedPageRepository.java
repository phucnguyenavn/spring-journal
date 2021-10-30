package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springutility.model.journal.UserCreatedPage;

public interface UserCreatedPageRepository extends JpaRepository<UserCreatedPage, Integer> {

    @Query(value = "Select u " +
            "from UserCreatedPage u " +
            "where u.userId=?1")
    UserCreatedPage findByUserId(Integer userId);
}
