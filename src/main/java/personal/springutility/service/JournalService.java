package personal.springutility.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import personal.springutility.dto.JournalList;
import personal.springutility.dto.SyncDto;
import personal.springutility.exception.model.ERROR;
import personal.springutility.model.journal.Journal;
import personal.springutility.model.journal.UserJournal;
import personal.springutility.model.sync.JournalSync;
import personal.springutility.repository.JournalRepository;
import personal.springutility.repository.JournalSyncRepository;
import personal.springutility.repository.UserJournalRepository;
import personal.springutility.util.Mappers;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class JournalService {


    private final UserJournalRepository userJournalRepository;
    private final JournalRepository journalRepository;
    private final JournalSyncRepository journalSyncRepository;
    private final Mappers modelMapper;

    public JournalService(UserJournalRepository userJournalRepository, JournalRepository journalRepository, JournalSyncRepository journalSyncRepository, Mappers modelMapper) {
        this.userJournalRepository = userJournalRepository;
        this.journalRepository = journalRepository;
        this.journalSyncRepository = journalSyncRepository;
        this.modelMapper = modelMapper;
    }


    public Integer findUserJournalId(Integer userId) {
        try {
            return userJournalRepository.findUserJournalId(userId);
        } catch (DataAccessException ex) {
            throw ERROR.DATA_NOT_FOUND;
        }
    }

    //Confirm the right userId and journal Id
    //Turn journalDTO to journal
    //Add those to journal table
    //Set push time
    public void pushJournals(JournalList journalList) {
        try {
            UserJournal userJournal = userJournalRepository
                    .findByIdAndUserId(journalList.getUserJournalId(),
                            journalList.getUserId());
            if (userJournal == null) {
                throw ERROR.DATA_NOT_FOUND;
            }
            List<Journal> journals = modelMapper.mapList(journalList.getJournals(), Journal.class, userJournal);
            journalRepository.saveAllAndFlush(journals);

        } catch (DataAccessException ex) {
            throw ERROR.SERVER_WENT_WRONG;
        }
    }

    public String instructJournalSync(SyncDto syncDto) {
        try {
            Optional<JournalSync> journalSync = journalSyncRepository
                    .findById(syncDto.getUserId(), syncDto.getId());
            return instruction(journalSync.orElseThrow(
                    () -> ERROR.DATA_NOT_FOUND), syncDto.getJournalLength());
        } catch (DataAccessException ex) {
            throw ERROR.DATA_NOT_FOUND;
        }
    }

    private String instruction(JournalSync journalSync, Integer journalLength) {
        String PULL = "PULL", PUSH = "PUSH", NONE = "NONE";
        long dbJournalLength = journalRepository
                .count(journalSync.getId().getUserJournalId(),
                        journalSync.getId().getUserId());
        if (dbJournalLength == journalLength) {
            return NONE;
        }
        return dbJournalLength > journalLength ? PULL : PUSH;
    }

}
