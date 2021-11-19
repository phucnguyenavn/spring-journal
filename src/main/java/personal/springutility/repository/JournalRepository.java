package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import personal.springutility.model.journal.Journal;

@Transactional
public interface JournalRepository extends JpaRepository<Journal, Integer> {

    @Query(value = "SELECT count(j) " +
            "FROM Journal j " +
            "WHERE j.userJournal.id = ?1 " +
            "and j.userJournal.userId =?2")
    Integer count(Integer userJournalId, Integer userId);
}
