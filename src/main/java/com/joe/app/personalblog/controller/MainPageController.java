package com.joe.app.personalblog.controller;

import com.joe.app.personalblog.model.Blog;
import com.joe.app.personalblog.service.BlogConfigurationService;
import com.joe.app.personalblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mainpage")
public class MainPageController {

    @Autowired
    private BlogService blogService;

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

    @RequestMapping(value = "/findAllBlogsWithoutContent", method = RequestMethod.GET)
    public List<Blog> findAllBlogsWithoutContent() {
        return blogService.findAllBlogWithoutContent();
    }

    @RequestMapping(value = "findBlogById/{blogId}", method = RequestMethod.GET)
    public Blog findBlogById(@PathVariable String blogId) {
        return blogService.findBlogById(blogId);
    }
}
