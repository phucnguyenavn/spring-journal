package personal.springjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springjournal.model.sync.JournalSync;

import java.util.Optional;

public interface JournalSyncRepository extends JpaRepository<JournalSync, Integer> {

    @Query(value = "SELECT js from JournalSync js " +
            "WHERE js.id.userId =?1 and js.id.userJournalId =?2")
    Optional<JournalSync> findById(Integer userId, Integer userJournalId);
}
