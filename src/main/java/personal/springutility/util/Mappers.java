package personal.springutility.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import personal.springutility.dto.PartOfPageDto;
import personal.springutility.model.journal.Page;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappers {

    @Autowired
    private ModelMapper modelMapper;

    public  <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
