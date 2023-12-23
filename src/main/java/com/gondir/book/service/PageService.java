package com.gondir.book.service;

import com.gondir.book.entity.Page;

import java.io.IOException;
import java.util.Map;

public interface PageService {

    Map<String, Page> loadStory() throws IOException;
}
