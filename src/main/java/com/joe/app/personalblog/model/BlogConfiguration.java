package com.joe.app.personalblog.model;

import java.util.Map;

public class BlogConfiguration {
    private String configName;
    private Map<String, String> configProp;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Map<String, String> getConfigProp() {
        return configProp;
    }

    public void setConfigProp(Map<String, String> configProp) {
        this.configProp = configProp;
    }
}
