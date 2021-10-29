package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.springutility.model.journal.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {


}
