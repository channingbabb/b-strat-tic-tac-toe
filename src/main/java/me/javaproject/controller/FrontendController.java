package me.javaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

    @GetMapping("")
    public String index() {
        return "forward:/frontend/index.html";
    }
}
