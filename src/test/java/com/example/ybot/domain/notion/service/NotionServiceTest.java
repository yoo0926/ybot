package com.example.ybot.domain.notion.service;

import com.example.ybot.common.config.DomainConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotionServiceTest {
    @Autowired
    TestRestTemplate template;
    @Autowired
    DomainConfig domainConfig;

    @Test
    void post_getDataBaseQuery() {
        String url = domainConfig.getDataBaseQueryUrl();
        ResponseEntity<String> response = template.postForEntity(url, null, String.class);

        String result = response.getBody();
        Assertions.assertThat(result).isNotEmpty();
    }
}
