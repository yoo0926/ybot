package com.example.ybot.domain.notion.service;

import com.example.ybot.common.config.DomainConfig;
import com.example.ybot.domain.notion.repository.Notion;
import com.example.ybot.domain.notion.repository.NotionRepository;
import com.example.ybot.domain.notion.service.dto.NotionRsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class NotionService {
    private final RestTemplate template;
    private final DomainConfig domainConfig;
    private final NotionRepository notionRepository;

    public NotionRsDto getDataBase() {
        String url = domainConfig.getDataBaseQueryUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add(DomainConfig.NOTION_VERSION, domainConfig.getNotionVersion());
        headers.setBearerAuth(domainConfig.getNotionKey());

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<NotionRsDto> response = template.postForEntity(url, entity, NotionRsDto.class);

        HttpStatusCode statusCode = response.getStatusCode();

        return response.getBody();
    }

    public List<Notion> saveAllNotion(NotionRsDto notionRsDto) {
        List<Notion> list = notionRsDto.getResults().stream().map(result -> {
            String storeName = result.getProperties().getName().getTitle()
                    .stream().map(title -> title.getText().getContent())
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            String category = result.getProperties().getCategory().getSelect().getName();

            return Notion.builder().title(storeName).category(category).build();
        }).toList();

        return notionRepository.saveAll(list);
    }
}
