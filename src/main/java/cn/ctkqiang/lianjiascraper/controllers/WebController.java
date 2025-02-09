package cn.ctkqiang.lianjiascraper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class WebController {
    private final Scrapper scrapper;

    public WebController() {
        this.scrapper = new Scrapper();
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "rs北京") String province, Model model) {
        try {
            model.addAttribute("houses", scrapper.scrapeLianjia(province));
            model.addAttribute("province", province);
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index";
    }
}