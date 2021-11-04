package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import personal.springutility.model.journal.Page;

import java.util.List;

@Transactional
public interface PageRepository extends JpaRepository<Page, Integer> {

    @Query(value = "Select " +
            "new Page(p.id, p.title, p.emoji,p.created) " +
            "from Page p where p.userCreatedPage.id=?2 " +
            "and p.userCreatedPage.userId=?1 order by p.id desc ")
    List<Page> findAll(Integer userId, Integer createdPageId);

    @Query(value = "Select p from Page p " +
            "where p.id=?1 and p.userCreatedPage.id = ?2")
    Page findOne(Integer pageId, Integer createdPageId);

    @Modifying
    @Query(value = "Delete from Page p " +
            "where p.id = :pageId and exists " +
            "(select u from UserCreatedPage u " +
            "where  u.id= :createdPageId and " +
            "u.userId= :userId)"
    )
    void deleteOne(@Param("userId") Integer userId,
                   @Param("createdPageId") Integer createdPageId,
                   @Param("pageId") Integer pageId);

}
