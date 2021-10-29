package personal.springutility.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import personal.springutility.dto.PartOfPageDto;
import personal.springutility.dto.UserAddPageDto;
import personal.springutility.model.journal.Page;
import personal.springutility.service.JournalService;

import java.util.List;

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

    @PostMapping(Endpoints.FIND_ALL)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PartOfPageDto> findAll(@PathVariable Integer userId,
                                       @PathVariable Integer createdPageId){
        return journalService.findAll(userId, createdPageId);
    }
}
