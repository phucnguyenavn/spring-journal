package personal.springjournal.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.springjournal.dto.SyncIdDto;
import personal.springjournal.dto.journal.JournalDto;
import personal.springjournal.dto.journal.JournalList;
import personal.springjournal.dto.journal.SyncDto;
import personal.springjournal.service.JournalService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Mappings.JOURNAL)
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping(Endpoints.FIND_USER_JOURNAL_ID)
    public ResponseEntity<?> findUserJournalId(@Param("userId") Integer userId) {
        Integer id = journalService.findUserJournalId(userId);
        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }

    @PostMapping(Endpoints.PUSH_JOURNALS)
    public ResponseEntity<?> pushJournals(@RequestBody JournalList journalList) {
        journalService.pushJournals(journalList);
        return ResponseEntity.ok("Successfully pulled journals");
    }

    @PostMapping(Endpoints.PULL_JOURNAL)
    @ResponseBody
    public List<JournalDto> pullJournals(@RequestBody SyncIdDto syncIdDto) {
        return journalService.pullJournals(syncIdDto);
    }

    @PostMapping(Endpoints.INSTRUCT_JOURNAL_SYNC)
    public ResponseEntity<?> instructJournalSync(@RequestBody SyncDto syncDto) {
        String instruction = journalService.instructJournalSync(syncDto);
        return ResponseEntity.ok(Collections.singletonMap("instruction", instruction));
    }
}
