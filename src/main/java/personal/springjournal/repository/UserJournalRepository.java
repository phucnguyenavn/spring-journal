package personal.springjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springjournal.model.journal.UserJournal;

public interface UserJournalRepository extends JpaRepository<UserJournal, Integer> {

    @Query(value = "SELECT u.id " +
            "FROM UserJournal u " +
            "WHERE u.userId=?1")
    Integer findUserJournalId(Integer userId);

    UserJournal findByIdAndUserId(Integer userJournalId, Integer userId);
}
