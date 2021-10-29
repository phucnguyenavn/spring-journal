package personal.springutility.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import personal.springutility.dto.UserAddPageDto;
import personal.springutility.exception.DataNotFound;
import personal.springutility.exception.ServerError;
import personal.springutility.model.journal.Page;
import personal.springutility.model.journal.RatingScale;
import personal.springutility.model.journal.UserCreatedPage;
import personal.springutility.repository.PageRepository;
import personal.springutility.repository.UserCreatedPageRepository;
import personal.springutility.util.StringUtils;

@Log4j2
@Service
public class JournalService {
    private static final String ADD_ERROR = "Could not add new page";
    private static final String ADD_ERROR_USER = ", invalid user id : %d ";
    private final PageRepository pageRepository;
    private final UserCreatedPageRepository userCreatedPageRepository;
    private final StringUtils stringUtils;

    public JournalService(PageRepository pageRepository, UserCreatedPageRepository userCreatedPageRepository, StringUtils stringUtils) {
        this.pageRepository = pageRepository;
        this.userCreatedPageRepository = userCreatedPageRepository;
        this.stringUtils = stringUtils;
    }


    public void addPage(Integer userId, UserAddPageDto userAddPageDto) {
        try {
            UserCreatedPage userCreatedPage = userCreatedPageRepository.findByUserId(userId);
            Page page = toPage(userAddPageDto);
            page.setUserCreatedPage(userCreatedPage);
            pageRepository.save(page);
        } catch (DataAccessException ex) {
            throw new ServerError(ADD_ERROR);
        } catch (NullPointerException ex) {
            throw new DataNotFound(String.format(ADD_ERROR + ADD_ERROR_USER, userId));
        }
    }

    private Page toPage(UserAddPageDto userAddPageDto) {
        return Page.builder()
                .title(userAddPageDto.getTitle())
                .content(userAddPageDto.getContent())
                .created(userAddPageDto.getCreated())
                .emoji(stringUtils.toByte(userAddPageDto.getIcon()))
                .scale(RatingScale.of(userAddPageDto.getScale()))
                .build();
    }

}
