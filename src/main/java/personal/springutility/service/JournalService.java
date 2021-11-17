package personal.springutility.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import personal.springutility.exception.DataNotFound;
import personal.springutility.repository.UserJournalRepository;

@Log4j2
@Service
public class JournalService {

    private final UserJournalRepository userJournalRepository;

    public JournalService(UserJournalRepository userJournalRepository) {
        this.userJournalRepository = userJournalRepository;
    }


    public Integer findUserJournalId(Integer userId) {
        try{
            return userJournalRepository.findUserJournalId(userId);
        }catch (DataAccessException ex){
            throw new DataNotFound(String.format("Could not find journal id with user id : %d",userId));
        }
    }
}
