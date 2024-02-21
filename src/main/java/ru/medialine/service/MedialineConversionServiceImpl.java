package ru.medialine.service;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MedialineConversionServiceImpl extends GenericConversionService implements IMedialineConversionService {
    @Override
    public <T, U> List<U> convert(Collection<T> items, Class<U> clazz) {
        if (items == null) {
            return new ArrayList<>(0);
        }
        return items.stream().map(item -> convert(item, clazz)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
