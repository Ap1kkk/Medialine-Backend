package ru.medialine.context;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class EmailContext {
    private String sender;
    private String receiver;
    private String subject;
    private String[] languages;
    private String templateLocation;
    private Map<String, Object> context;
}
