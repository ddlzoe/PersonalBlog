package com.joe.app.personalblog.controller;

import com.joe.app.personalblog.service.BlogConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/mainpage")
public class MainPageController {

    @Autowired
    private BlogConfigurationService blogConfigSvc;

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World...";
    }

    @RequestMapping(value = "/config/topheader", method = RequestMethod.GET)
    public Map<String, String> getTopHeaderConfig() {
        return blogConfigSvc.getConfiguration("topHeader");
    }
}
