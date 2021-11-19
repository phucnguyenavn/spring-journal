package personal.springutility.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.springutility.dto.JournalList;
import personal.springutility.dto.SyncDto;
import personal.springutility.service.JournalService;

import java.util.Collections;

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

    @PostMapping(Endpoints.INSTRUCT_JOURNAL_SYNC)
    public ResponseEntity<?> instructJournalSync(@RequestBody SyncDto syncDto) {
        String instruction = journalService.instructJournalSync(syncDto);
        return ResponseEntity.ok(Collections.singletonMap("instruction", instruction));
    }
}
