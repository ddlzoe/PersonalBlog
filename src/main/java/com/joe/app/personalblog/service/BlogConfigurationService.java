package com.joe.app.personalblog.service;

import com.joe.app.personalblog.model.BlogConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class BlogConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(BlogConfiguration.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map<String, String> getConfiguration(String configName) {
        logger.info("Get configuration of config name: {}", configName);
        Criteria criteria = Criteria.where("configName").is(configName);
        Query query = new Query(criteria);
        List<BlogConfiguration> configs = mongoTemplate.find(query, BlogConfiguration.class, "BlogConfiguration");
        if(configs == null || configs.isEmpty()) {
            logger.warn("Cannot get configuration of Config Name: {}", configName);
            return Collections.emptyMap();
        }
        if(configs.size() > 1) {
            logger.error("Duplicated configs !!! Pls. remove one config of {}. Return first result.", configName);
        }
        return configs.get(0).getConfigProp();
    }

}
