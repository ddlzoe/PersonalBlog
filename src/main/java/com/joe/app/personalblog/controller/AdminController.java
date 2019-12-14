package com.joe.app.personalblog.controller;

import com.joe.app.personalblog.model.AdminUser;
import com.joe.app.personalblog.model.Blog;
import com.joe.app.personalblog.service.AdminService;
import com.joe.app.personalblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody AdminUser user) {
        boolean result = adminService.addUser(user);
        if(result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return ResponseEntity.ok().body("Failed");
        }
    }

    @RequestMapping(value = "addBlog", method = RequestMethod.POST)
    public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
        boolean result = blogService.insertNewBlog(blog);
        if(result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return ResponseEntity.ok().body("Failed");
        }
    }

    @RequestMapping(value = "deleteBlog/{blogId}", method = RequestMethod.POST)
    public ResponseEntity<String> deleteBlog(@PathVariable String blogId) {
        boolean result = blogService.deleteBlog(blogId);
        if(result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return ResponseEntity.ok().body("Failed");
        }
    }

    @RequestMapping(value = "updateBlog", method = RequestMethod.POST)
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog) {
        boolean result = blogService.updateNewBlog(blog);
        if(result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return ResponseEntity.ok().body("Failed");
        }
    }

    @RequestMapping(value = "findAllBlogs", method = RequestMethod.POST)
    public ResponseEntity<List<Blog>> findAllBlogs() {
        return ResponseEntity.ok().body(blogService.findAllBlogWithoutContent());
    }

    @RequestMapping(value = "findBlogById/{blogId}", method = RequestMethod.GET)
    public ResponseEntity<Blog> findBlogById(@PathVariable String blogId) {
        return ResponseEntity.ok().body(blogService.findBlogById(blogId));
    }
}
