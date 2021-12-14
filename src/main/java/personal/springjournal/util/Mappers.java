package personal.springjournal.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import personal.springjournal.dto.journal.JournalDto;
import personal.springjournal.model.journal.Journal;
import personal.springjournal.model.journal.Mood;
import personal.springjournal.model.journal.UserJournal;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappers {


    private final ModelMapper modelMapper;

    public Mappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Journal> mapList(List<JournalDto> source, Class<Journal> journalClass, UserJournal userJournal) {
        return source
                .stream()
                .map(element -> {
                    Journal journal = modelMapper.map(element, journalClass);
                    journal.setMood(Mood.of(element.getMood()));
                    journal.setUserJournal(userJournal);
                    return journal;
                })
                .collect(Collectors.toList());
    }

    public List<JournalDto> mapList(List<Journal> source, Class<JournalDto> journalDtoClass) {

        return source
                .stream()
                .map(element -> modelMapper.map(element, journalDtoClass))
                .collect(Collectors.toList());
    }
}
