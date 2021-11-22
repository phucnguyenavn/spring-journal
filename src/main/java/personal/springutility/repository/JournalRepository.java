package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import personal.springutility.dto.JournalDto;
import personal.springutility.dto.SyncIdDto;
import personal.springutility.model.journal.Journal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface JournalRepository extends JpaRepository<Journal, Integer> {

    @Query(value = "SELECT count(j) " +
            "FROM Journal j " +
            "WHERE j.userJournal.id = ?1 " +
            "and j.userJournal.userId =?2")
    Integer count(Integer userJournalId, Integer userId);


    Optional<Journal> findByCreated(LocalDate date);

    @Modifying
    @Query(value = "UPDATE Journal j SET j = ?1")
    void update(Journal journal);

    @Query(value = "SELECT j from Journal j " +
            "WHERE j.userJournal.id =?2 " +
            "and j.userJournal.userId =?1")
    List<Journal> findAllBySyncId(Integer userId, Integer id);

}
