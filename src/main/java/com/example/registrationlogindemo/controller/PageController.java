package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Page;
import com.example.registrationlogindemo.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Map;

@Controller
public class PageController {

    private final PageService pageService;

    public PageController(PageService storyService) {
        this.pageService = storyService;
    }

    @GetMapping("/page/{pageNumber}")
    public String getPage(@PathVariable String pageNumber, Model model) throws IOException {
        Map<String, Page> story = pageService.loadStory();
        Page currentPage = story.get(pageNumber);
        model.addAttribute("currentPage", currentPage);
        return "page";
    }
}

