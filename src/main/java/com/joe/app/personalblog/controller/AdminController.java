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

//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    public ResponseEntity<String> addUser(@RequestBody AdminUser user) {
//        boolean result = adminService.addUser(user);
//        if(result) {
//            return ResponseEntity.ok().body("Success");
//        } else {
//            return ResponseEntity.ok().body("Failed");
//        }
//    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ResponseEntity<String> addUser() {
        AdminUser user = new AdminUser();
        user.setUserName("ddlzoe");
        user.setPassword("Joe314159");
        List<String> roles = new ArrayList<>();
        roles.add("SUPER_ADMIN");
        roles.add("ADMIN");
        user.setRoles(roles);
        boolean result = adminService.addUser(user);
        if(result) {
            return ResponseEntity.ok().body("Success");
        } else {
            return ResponseEntity.ok().body("Failed");
        }
    }

}
