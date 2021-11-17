package personal.springutility.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> findUserJournalId(@Param("userId") Integer userId){
        Integer id = journalService.findUserJournalId(userId);
        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }
}
