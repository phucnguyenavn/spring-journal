package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springutility.model.journal.UserJournal;

public interface UserJournalRepository extends JpaRepository<UserJournal, Integer> {

    @Query(value ="SELECT u.id " +
            "FROM UserJournal u " +
            "WHERE u.userId=?1")
    Integer findUserJournalId(Integer userId);


}
