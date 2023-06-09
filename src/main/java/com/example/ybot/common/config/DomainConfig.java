package com.example.ybot.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "domain")
public class DomainConfig {
    private Notion notion;

    @Getter
    @Setter
    public static class Notion {
        private String url;
        private String secretKey;
        private String databaseId;
    }

    public String getDataBaseQueryUrl() {
        return this.notion.url + "/databases/" + this.notion.databaseId + "/query";
    }
}
