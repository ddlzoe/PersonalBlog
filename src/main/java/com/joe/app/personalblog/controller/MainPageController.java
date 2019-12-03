package com.joe.app.personalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainPageController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String helloWorld() {
        List<HashMap> result  = mongoTemplate.findAll(HashMap.class, "helloWorld");
        Map<String, String> firstResult = result.get(0);
        return firstResult.get("helloWorld");
    }

}
