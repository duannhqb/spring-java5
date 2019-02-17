package edu.poly.sprg.manager.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LanguageController {

    @RequestMapping("/{locale:en|vi}/language")
    public String login2(Model model, @RequestParam String p) {
        return "redirect:" + p;
    }
}
