package com.movietheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/")
public class ApiController {
    @RequestMapping("/")
    public String index(Model model) {
        return "indexApi";
    }
}
