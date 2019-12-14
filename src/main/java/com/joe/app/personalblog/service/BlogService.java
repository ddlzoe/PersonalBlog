package com.joe.app.personalblog.service;

import com.joe.app.personalblog.model.Blog;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Blog> findAllBlogWithoutContent() {
        Query query = new Query();
        query.fields().exclude("content");
        return mongoTemplate.find(query, Blog.class);
    }

    public boolean insertNewBlog(Blog blog) {
        try {
            blog.setCreatedTime(DateFormatUtils.format(new Date(), DATE_FORMAT));
            mongoTemplate.insert(blog);
            return true;
        } catch (Exception e) {
            logger.error("Failed to insert new Blog. ", e);
            return false;
        }
    }

    public boolean updateNewBlog(Blog blog) {
        try {
            blog.setUpdatedTime(DateFormatUtils.format(new Date(), DATE_FORMAT));
            mongoTemplate.save(blog);
            return true;
        } catch (Exception e) {
            logger.error("Failed to update Blog. ", e);
            return false;
        }

    }

    public boolean deleteBlog(String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(query, Blog.class);
            return true;
        } catch (Exception e) {
            logger.error("Failed to delete Blog. ", e);
            return false;
        }
    }

    public Blog findBlogById(String id) {
        try {
            Query query = new Query(Criteria.where("id").is(id));
            return mongoTemplate.findOne(query, Blog.class);
        } catch (Exception e ) {
            logger.error("Failed to get Blog of {}", id, e);
            return null;
        }
    }
}
