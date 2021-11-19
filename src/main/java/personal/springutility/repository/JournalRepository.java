package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import personal.springutility.model.journal.Journal;

@Transactional
public interface JournalRepository extends JpaRepository<Journal, Integer> {


}
