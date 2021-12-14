package personal.springjournal.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import personal.springjournal.dto.SyncIdDto;
import personal.springjournal.dto.journal.JournalDto;
import personal.springjournal.dto.journal.JournalList;
import personal.springjournal.dto.journal.SyncDto;
import personal.springjournal.exception.model.ERROR;
import personal.springjournal.model.journal.Journal;
import personal.springjournal.model.journal.UserJournal;
import personal.springjournal.model.sync.JournalSync;
import personal.springjournal.repository.JournalRepository;
import personal.springjournal.repository.JournalSyncRepository;
import personal.springjournal.repository.UserJournalRepository;
import personal.springjournal.util.Mappers;

import java.time.LocalDateTime;
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
            int id = journalList.getSyncIdDto().getId();
            int userId = journalList.getSyncIdDto().getUserId();
            UserJournal userJournal = userJournalRepository
                    .findByIdAndUserId(id, userId);
            if (userJournal == null) {
                throw ERROR.DATA_NOT_FOUND;
            }
            List<Journal> journals = modelMapper.mapList(journalList.getJournals(),
                    Journal.class, userJournal);
            addOrUpdate(journals);
            Optional<JournalSync> journalSync = journalSyncRepository.findById(userId, id);
            journalSync.ifPresent(j -> {
                j.setPushed(LocalDateTime.now());
                journalSyncRepository.save(journalSync.get());
            });
        } catch (DataAccessException ex) {
            throw ERROR.SERVER_WENT_WRONG;
        }
    }

    public List<JournalDto> pullJournals(SyncIdDto syncIdDto) {
        try {
            List<Journal> journals = journalRepository.findAllBySyncId(syncIdDto.getUserId(), syncIdDto.getId());
            List<JournalDto> journalDtos = modelMapper.mapList(journals, JournalDto.class);
            Optional<JournalSync> journalSync = journalSyncRepository.findById(syncIdDto.getUserId(), syncIdDto.getId());
            journalSync.ifPresent(j -> {
                j.setPulled(LocalDateTime.now());
                journalSyncRepository.save(journalSync.get());
            });
            return journalDtos;
        } catch (DataAccessException ex) {
            throw ERROR.SERVER_WENT_WRONG;
        }
    }

    public String instructJournalSync(SyncDto syncDto) {
        try {
            Optional<JournalSync> journalSync = journalSyncRepository
                    .findById(syncDto.getSyncIdDto().getUserId(), syncDto.getSyncIdDto().getId());
            return instruction(journalSync.orElseThrow(
                    () -> ERROR.DATA_NOT_FOUND), syncDto.getJournalLength());
        } catch (DataAccessException ex) {
            throw ERROR.DATA_NOT_FOUND;
        }
    }

    private String instruction(JournalSync journalSync, Integer journalLength) {
        int userJournalId = journalSync.getId().getUserJournalId();
        int userId = journalSync.getId().getUserId();
        long dbJournalLength = journalRepository.count(userJournalId, userId);
        return getInstruction(dbJournalLength, journalLength, journalSync);
    }

    private void addOrUpdate(List<Journal> journals) {
        for (Journal journalInput : journals) {
            Optional<Journal> journal = journalRepository.
                    findByCreated(journalInput.getCreated());
            if (journal.isPresent()) {
                journalRepository.update(journal.get().getCreated(), journalInput);
            } else
                journalRepository.save(journalInput);
        }
    }

    private String getInstruction(long dbJournalLength, Integer journalLength, JournalSync journalSync) {
        String PULL = "PULL", PUSH = "PUSH", NONE = "NONE";
        if (dbJournalLength == journalLength) {
            if (journalSync.getPulled().isBefore(journalSync.getPushed())) {
                return PULL;
            } else if (journalSync.getPulled().isAfter(journalSync.getPushed())) {
                return PUSH;
            }
            return NONE;
        }
        return dbJournalLength > journalLength ? PULL : PUSH;
    }
}
