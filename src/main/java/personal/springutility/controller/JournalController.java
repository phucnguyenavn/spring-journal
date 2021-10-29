package personal.springutility.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import personal.springutility.dto.UserAddPageDto;
import personal.springutility.service.JournalService;

@RestController
@RequestMapping(Mappings.JOURNAL)
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }


    @PostMapping(Endpoints.ADD_PAGE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPage(@PathVariable Integer userId,
                        @RequestBody UserAddPageDto userAddPageDto) {
        journalService.addPage(userId, userAddPageDto);
    }
}
