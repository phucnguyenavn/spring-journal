package personal.springutility.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.springutility.dto.JournalDto;
import personal.springutility.model.journal.Journal;
import personal.springutility.model.journal.UserJournal;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappers {

    @Autowired
    private ModelMapper modelMapper;

    public List<Journal> mapList(List<JournalDto> source, Class<Journal> journalClass, UserJournal userJournal) {
        return source
                .stream()
                .map(element -> {
                   Journal journal =  modelMapper.map(element, journalClass);
                   journal.setUserJournal(userJournal);
                   return journal;
                })
                .collect(Collectors.toList());
    }

}
