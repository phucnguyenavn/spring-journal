package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.springutility.model.journal.Page;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Integer> {

    @Query(value = "Select " +
            "new Page(p.id, p.title, p.emoji,p.created) " +
            "from Page p where p.userCreatedPage.id=?2 " +
            "and p.userCreatedPage.userId=?1")
    List<Page> findAll(Integer userId, Integer createdPageId);
}
