package ru.medialine.service;

import org.springframework.core.convert.ConversionService;

import java.util.Collection;
import java.util.List;

public interface IMedialineConversionService extends ConversionService {

    <T, U> List<U> convert(Collection<T> items, Class<U> clazz);
}