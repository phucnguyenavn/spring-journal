package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springutility.model.sync.JournalSync;

public interface JournalSyncRepository extends JpaRepository<JournalSync, Integer> {

    @Query(value = "SELECT js from JournalSync js " +
            "WHERE js.id.userId =?1 and js.id.userJournalId =?2")
    JournalSync findById(Integer userId, Integer userJournalId);
}
