package edu.poly.sprg.manager.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String defaultMapping() {
        return "index";
    }
}
