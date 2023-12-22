package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Page;
import com.example.registrationlogindemo.service.PageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {
    public Map<String, Page> loadStory() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("pages.json").getInputStream();
        return objectMapper.readValue(inputStream, new TypeReference<Map<String, Page>>() {});
    }
}
