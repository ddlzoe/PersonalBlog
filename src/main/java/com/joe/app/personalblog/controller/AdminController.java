package com.joe.app.personalblog.controller;

import com.joe.app.personalblog.model.AdminUser;
import com.joe.app.personalblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody AdminUser user) {
        boolean result = adminService.addUser(user);
        if(result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return ResponseEntity.ok().body("Failed");
        }
    }

}
