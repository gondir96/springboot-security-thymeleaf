package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Page;

import java.io.IOException;
import java.util.Map;

public interface PageService {

    Map<String, Page> loadStory() throws IOException;
}
