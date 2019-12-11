package com.joe.app.personalblog.service;

import com.joe.app.personalblog.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Query query = new Query(Criteria.where("userName").is(userName));
        AdminUser user = mongoTemplate.findOne(query, AdminUser.class);
        if(user == null) {
            throw new UsernameNotFoundException(userName);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
