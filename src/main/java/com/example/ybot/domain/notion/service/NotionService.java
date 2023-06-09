package com.example.ybot.domain.notion.service;

import com.example.ybot.common.config.DomainConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class NotionService {
    private final RestTemplate template;
    private final DomainConfig domainConfig;

    public String getDataBase() {
        String url = domainConfig.getDataBaseQueryUrl();
        ResponseEntity<String> response = template.postForEntity(url, null, String.class);

        HttpStatusCode statusCode = response.getStatusCode();
        HttpHeaders headers = response.getHeaders();
        String body = response.getBody();

        return body;
    }
}
