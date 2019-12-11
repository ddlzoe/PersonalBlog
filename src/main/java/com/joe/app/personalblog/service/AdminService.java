package com.joe.app.personalblog.service;

import com.joe.app.personalblog.model.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(AdminUser user) {
        logger.info("Adding user: {}", user.getUserName());
        try {
            String pwd = user.getPassword();
            user.setPassword(passwordEncoder.encode(pwd));
            mongoTemplate.insert(user);
            return true;
        } catch (Exception e) {
            logger.error("Failed to add new user {}", user.getUserName(), e);
            return false;
        }

    }

}
